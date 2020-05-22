package top.oldwei.netty.client.controller;

import cn.hutool.core.date.SystemClock;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.netty.client.util.CacheUtil;
import top.oldwei.netty.client.util.FileUtil;
import top.oldwei.netty.common.domain.FileInfo;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.StartFolderTransferRequestPacket;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@RequestMapping("/netty")
@RestController
@Slf4j
public class NettyController {


    @Resource
    private FileUtil fileUtil;



    @RequestMapping("/send-path")
    public String testSendPath(String channelId,String filePath){

        Channel channel = CacheUtil.sessionMap.get("weizd_channel");
        if(StringUtils.isEmpty(channel)){
            return "fail";
        }

        return "success";
    }




    @RequestMapping("/send-file")
    public String testSendFile(String channelId,String filePath){

        Channel channel = CacheUtil.sessionMap.get("weizd_channel");
        if(StringUtils.isEmpty(channel)){
            return "fail";
        }

        sendFile(filePath,channel);

        return "success";
    }

    @RequestMapping("/send-folder")
    public String testSendFolder(String channelId,String filePath){

        Channel channel = CacheUtil.sessionMap.get("weizd_channel");
        if(StringUtils.isEmpty(channel)){
            return "fail";
        }
        log.info("channel is activate: {},is open: {},is registered:{}, is writable: {}",channel.isActive(),channel.isOpen(),channel.isRegistered(),channel.isWritable());
        sendFolder(filePath,channel);

        return "success";
    }

    public void sendFolder(String filePath,Channel channel){
        filePath = "/home/weizd/dataset/dataset";
        File[] files = cn.hutool.core.io.FileUtil.ls(filePath);
        StartFolderTransferRequestPacket startFolderTransferRequestPacket = new StartFolderTransferRequestPacket();
        startFolderTransferRequestPacket.setBasePath(filePath);
        FileInfo fileInfo = null;
        for(File file:files){
            fileInfo = new FileInfo();
            fileInfo.setFileName(file.getName());
            fileInfo.setFilePath(file.getPath());
            fileInfo.setMd5(fileUtil.getFileMd5(file.getPath()));
            fileInfo.setFileSize(file.length());
            fileInfo.setRelativePath(file.getPath().substring(filePath.length()));
            startFolderTransferRequestPacket.getFileList().add(fileInfo);
            CacheUtil.takeTime.put(fileInfo.getMd5(),SystemClock.now());
        }

        channel.writeAndFlush(startFolderTransferRequestPacket);

    }

    private void sendFile(String filePath,Channel channel){
        filePath = "/home/weizd/dataset/dataset/air_nohead.csv";

        File file = new File(filePath);

        long s = SystemClock.now();

        String md5 = fileUtil.getFileMd5(filePath);

        long startIndex = 0;
        byte[] bytes = FileUtil.getFileRangeByte(filePath,startIndex,1024*1024);
        if(null != bytes){
            FileTransferRequestPacket fileTransferRequestPacket = new FileTransferRequestPacket();
            fileTransferRequestPacket.setBytes(bytes);
            fileTransferRequestPacket.setFileName(file.getName());
            fileTransferRequestPacket.setFilePath(file.getPath());
            fileTransferRequestPacket.setStartPos(startIndex);
            fileTransferRequestPacket.setEndPos(startIndex+bytes.length);
            fileTransferRequestPacket.setMd5(md5);
            channel.writeAndFlush(fileTransferRequestPacket);
            CacheUtil.takeTime.put(md5,SystemClock.now());
        }else {
            log.error("transfer [{}] error,fileSize: {}",filePath,file.length());
        }

//        log.info("fileSize: {},startIndex:{},endIndex: {} send success take: {}",file.length(),startIndex,startIndex+bytes.length,SystemClock.now() -s);

    }


}
