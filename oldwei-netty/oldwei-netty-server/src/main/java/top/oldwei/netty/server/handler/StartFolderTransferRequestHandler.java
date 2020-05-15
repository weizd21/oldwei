package top.oldwei.netty.server.handler;

import cn.hutool.core.io.FileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import top.oldwei.netty.common.domain.FileInfo;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.StartFolderTransferRequestPacket;
import top.oldwei.netty.server.constant.Constant;
import top.oldwei.netty.server.domain.TransferFileInfo;
import top.oldwei.netty.server.util.DateUtil;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;


import static top.oldwei.netty.server.util.CacheUtil.fileInfoCache;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Slf4j
public class StartFolderTransferRequestHandler extends SimpleChannelInboundHandler<StartFolderTransferRequestPacket> {

    private String basePath = Constant.BASE_PATH;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, StartFolderTransferRequestPacket startFolderTransferPacket) throws Exception {
        // 获取保存路径
        String path = Paths.get(basePath, DateUtil.getDateTimeStr()).toString();

        log.info("path:{}",path);

        List<FileInfo> fileInfos = startFolderTransferPacket.getFileList();
        FileTransferResponsePacket fileTransferResponsePacket = null;
        long startIndex = 0;
        TransferFileInfo transferFileInfo = null;
        for (FileInfo fileInfo:fileInfos){
            fileTransferResponsePacket = new FileTransferResponsePacket();
            if(fileInfoCache.containsKey(fileInfo.getMd5())){
                // 存在
                transferFileInfo = fileInfoCache.get(fileInfo.getMd5());
                File currentFile  = new File(transferFileInfo.getCurrentFilePath());
                if(currentFile.exists() && currentFile.lastModified() == transferFileInfo.getLastModified()){
                    startIndex = transferFileInfo.getTransferPos();
                    // 移动文件
                    FileUtils.copyFile(new File(transferFileInfo.getCurrentFilePath()),new File(path+fileInfo.getRelativePath()));
                    log.info("移动[{}] 到 [{}] 成功",transferFileInfo.getCurrentFilePath(),path+fileInfo.getRelativePath());
                    if(transferFileInfo.getFileSize() == transferFileInfo.getTransferPos()){
                        // 已经传输完成
                        transferFileInfo.setLastModified(new File(path+fileInfo.getRelativePath()).lastModified());
                    }
                    transferFileInfo.setCurrentFilePath(path+fileInfo.getRelativePath());
                }
            }else{
                transferFileInfo = new TransferFileInfo();
                transferFileInfo.setCurrentFilePath(path+fileInfo.getRelativePath());
                transferFileInfo.setTransferPos(0);
                transferFileInfo.setSourceFilePath(fileInfo.getFilePath());
            }

            fileTransferResponsePacket.setStartPos(startIndex);
            fileTransferResponsePacket.setFileName(fileInfo.getFileName());
            fileTransferResponsePacket.setFilePath(fileInfo.getFilePath());
            fileTransferResponsePacket.setMd5(fileInfo.getMd5());
            fileTransferResponsePacket.setEndPos(-1L);
            fileTransferResponsePacket.setDestFilePath(path+fileInfo.getRelativePath());
            channelHandlerContext.channel().writeAndFlush(fileTransferResponsePacket);
            fileInfoCache.put(fileInfo.getMd5(),transferFileInfo);
        }
    }

}
