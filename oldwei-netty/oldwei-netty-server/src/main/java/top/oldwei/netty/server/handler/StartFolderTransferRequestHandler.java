package top.oldwei.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.domain.FileInfo;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.StartFolderTransferRequestPacket;
import top.oldwei.netty.server.constant.Constant;
import top.oldwei.netty.server.util.DateUtil;

import java.nio.file.Paths;
import java.util.List;

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
        for (FileInfo fileInfo:fileInfos){
            fileTransferResponsePacket = new FileTransferResponsePacket();
            fileTransferResponsePacket.setStartPos(0L);
            fileTransferResponsePacket.setFileName(fileInfo.getFileName());
            fileTransferResponsePacket.setFilePath(fileInfo.getFilePath());
            fileTransferResponsePacket.setMd5(fileInfo.getMd5());
            fileTransferResponsePacket.setEndPos(-1L);
            fileTransferResponsePacket.setDestFilePath(path+fileInfo.getRelativePath());
            channelHandlerContext.channel().writeAndFlush(fileTransferResponsePacket);
        }
    }

}
