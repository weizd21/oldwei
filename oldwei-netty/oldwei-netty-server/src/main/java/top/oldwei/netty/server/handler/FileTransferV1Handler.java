package top.oldwei.netty.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.packet.FileTransferPacketV1;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
@Slf4j
public class FileTransferV1Handler extends SimpleChannelInboundHandler<FileTransferPacketV1> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FileTransferPacketV1 fileTransferPacketV1) throws Exception {
        log.info("--------> {}", JSONObject.toJSONString(fileTransferPacketV1));
        String basePath = "/home/ap/netty-demo";

        File file = new File(Paths.get(basePath,fileTransferPacketV1.getFileName()).toString());
        if(!file.exists()){
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");

        randomAccessFile.seek(fileTransferPacketV1.getStartPos());
        randomAccessFile.write(fileTransferPacketV1.getBytes());
        randomAccessFile.close();



    }
}
