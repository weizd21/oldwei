package top.oldwei.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.StartFileTransferRequestPacket;
import top.oldwei.netty.server.constant.Constant;
import top.oldwei.netty.server.domain.TransferFileInfo;
import top.oldwei.netty.server.util.DateUtil;

import java.io.File;
import java.nio.file.Paths;

import static top.oldwei.netty.server.util.CacheUtil.fileInfoCache;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Slf4j
public class StartFileTransferRequestHandler extends SimpleChannelInboundHandler<StartFileTransferRequestPacket> {

    private String basePath = Constant.BASE_PATH;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StartFileTransferRequestPacket startFileTransferRequestPacket) throws Exception {

        // 获取保存路径
        String path = Paths.get(basePath, DateUtil.getDateTimeStr()).toString();
        TransferFileInfo transferFileInfo ;
        long startIndex = 0;
        if(fileInfoCache.containsKey(startFileTransferRequestPacket.getMd5())){
            // 已经传输过
            transferFileInfo = fileInfoCache.get(startFileTransferRequestPacket.getMd5());
            // 拷贝一份文件到 目标位置
            File currentFile = new File(transferFileInfo.getCurrentFilePath());
            if(currentFile.exists() && currentFile.lastModified() == transferFileInfo.getLastModified()){
                startIndex = transferFileInfo.getTransferPos();
                // 移动
                FileUtils.copyFile(currentFile,new File(Paths.get(path,startFileTransferRequestPacket.getFileName()).toString()));
                transferFileInfo.setLastModified(new File(Paths.get(path,startFileTransferRequestPacket.getFileName()).toString()).lastModified());
            }
            transferFileInfo.setTransferPos(0);
            transferFileInfo.setCurrentFilePath(Paths.get(path,startFileTransferRequestPacket.getFileName()).toString());
        }else {
            // 没有传输过
            transferFileInfo = new TransferFileInfo();
            transferFileInfo.setFileSize(startFileTransferRequestPacket.getFileSize());
            transferFileInfo.setTransferPos(0);
            transferFileInfo.setCurrentFilePath(Paths.get(path,startFileTransferRequestPacket.getFileName()).toString());
            transferFileInfo.setSourceFilePath(startFileTransferRequestPacket.getFilePath());
        }
        FileTransferResponsePacket fileTransferResponsePacket = new FileTransferResponsePacket();
        fileTransferResponsePacket.setStartPos(startIndex);
        fileTransferResponsePacket.setFileName(startFileTransferRequestPacket.getFileName());
        fileTransferResponsePacket.setFilePath(startFileTransferRequestPacket.getFilePath());
        fileTransferResponsePacket.setMd5(startFileTransferRequestPacket.getMd5());
        fileTransferResponsePacket.setEndPos(-1L);
        channelHandlerContext.channel().writeAndFlush(fileTransferResponsePacket);

        fileInfoCache.put(startFileTransferRequestPacket.getMd5(),transferFileInfo);


    }

}
