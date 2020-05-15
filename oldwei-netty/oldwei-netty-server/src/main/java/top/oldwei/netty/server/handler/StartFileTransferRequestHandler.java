package top.oldwei.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.StartFileTransferRequestPacket;
import top.oldwei.netty.server.constant.Constant;
import top.oldwei.netty.server.domain.TransferFileInfo;

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


        long startIndex = 0;
        if(fileInfoCache.containsKey(startFileTransferRequestPacket.getMd5())){
            // 已经传输过
            TransferFileInfo transferFileInfo = fileInfoCache.get(startFileTransferRequestPacket.getMd5());
            // 拷贝一份文件到 目标位置
//            FileUtil.copy()
//            startIndex = transferFileInfo.getTransferPos();
//            if()

            // todo

        }else {
            // 没有传输过
        }

        FileTransferResponsePacket fileTransferResponsePacket = new FileTransferResponsePacket();
        fileTransferResponsePacket.setStartPos(startIndex);
        fileTransferResponsePacket.setFileName(startFileTransferRequestPacket.getFileName());
        fileTransferResponsePacket.setFilePath(startFileTransferRequestPacket.getFilePath());
        fileTransferResponsePacket.setMd5(startFileTransferRequestPacket.getMd5());
        fileTransferResponsePacket.setEndPos(-1L);



    }

}
