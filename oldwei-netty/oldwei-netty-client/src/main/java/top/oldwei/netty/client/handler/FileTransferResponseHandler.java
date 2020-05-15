package top.oldwei.netty.client.handler;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.client.util.CacheUtil;
import top.oldwei.netty.client.util.FileUtil;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;

/**
 * @Author:weizd
 * @Date:20-5-14
 */
@Slf4j
public class FileTransferResponseHandler extends SimpleChannelInboundHandler<FileTransferResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FileTransferResponsePacket fileTransferResponsePacket) {
        log.debug("---> {}", JSONObject.toJSONString(fileTransferResponsePacket));

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
            fileTransferRequestPacket.setDestFilePath(fileTransferResponsePacket.getDestFilePath());
            channelHandlerContext.channel().writeAndFlush(fileTransferRequestPacket);
        }else {
            log.info("transfer [{}] to [{}] success,fileSize: {}, take: {} ms",fileTransferResponsePacket.getFilePath(),fileTransferResponsePacket.getDestFilePath(),startIndex,SystemClock.now() - CacheUtil.takeTime.get(fileTransferResponsePacket.getMd5()));
        }


    }





}
