package top.oldwei.netty.client.handler;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.oldwei.netty.client.util.FileUtil;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;

import javax.annotation.Resource;
import java.io.RandomAccessFile;

/**
 * @Author:weizd
 * @Date:20-5-14
 */
@Slf4j
public class FileTransferResponseHandler extends SimpleChannelInboundHandler<FileTransferResponsePacket> {


    private static long s = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FileTransferResponsePacket fileTransferResponsePacket) throws Exception {
//        log.info("---> {}", JSONObject.toJSONString(fileTransferResponsePacket));

        if(s == 0){
            s = SystemClock.now();
        }

        long startIndex = fileTransferResponsePacket.getStartPos();
        byte[] bytes = FileUtil.getFileRangeByte(fileTransferResponsePacket.getFilePath(),startIndex,1024*1024);
        if(null != bytes){
            FileTransferRequestPacket fileTransferRequestPacket = new FileTransferRequestPacket();
            fileTransferRequestPacket.setBytes(bytes);
            fileTransferRequestPacket.setFileName(fileTransferResponsePacket.getFileName());
            fileTransferRequestPacket.setFilePath(fileTransferResponsePacket.getFilePath());
            fileTransferRequestPacket.setStartPos(startIndex);
            fileTransferRequestPacket.setEndPos(startIndex+bytes.length);
            fileTransferRequestPacket.setMd5(fileTransferResponsePacket.getMd5());
            channelHandlerContext.channel().writeAndFlush(fileTransferRequestPacket);
        }else {
            log.info("transfer [{}] success,fileSize: {}, take: {} ",fileTransferResponsePacket.getFilePath(),startIndex,SystemClock.now() - s);
        }


    }





}
