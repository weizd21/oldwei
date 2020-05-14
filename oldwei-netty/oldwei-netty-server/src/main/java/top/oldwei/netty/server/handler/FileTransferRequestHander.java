package top.oldwei.netty.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.packet.FileTransferPacketV1;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
@Slf4j
public class FileTransferRequestHander extends SimpleChannelInboundHandler<FileTransferRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FileTransferRequestPacket fileTransferRequestPacket) throws Exception {
//        log.info("--------> {}", JSONObject.toJSONString(fileTransferRequestPacket));
        String basePath = "/home/ap/netty-demo";

        File file = new File(Paths.get(basePath,fileTransferRequestPacket.getFileName()).toString());
        if(!file.exists()){
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");

        randomAccessFile.seek(fileTransferRequestPacket.getStartPos());
        randomAccessFile.write(fileTransferRequestPacket.getBytes());
        randomAccessFile.close();


        FileTransferResponsePacket fileTransferResponsePacket = new FileTransferResponsePacket();
        fileTransferResponsePacket.setStartPos(fileTransferRequestPacket.getEndPos());
        fileTransferResponsePacket.setFileName(fileTransferRequestPacket.getFileName());
        fileTransferResponsePacket.setFilePath(fileTransferRequestPacket.getFilePath());
        fileTransferResponsePacket.setMd5(fileTransferRequestPacket.getMd5());
        fileTransferResponsePacket.setEndPos(-1L);

        channelHandlerContext.channel().writeAndFlush(fileTransferResponsePacket);

    }
}
