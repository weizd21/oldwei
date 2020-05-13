package top.oldwei.netty.client.init;

import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.SystemClock;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import top.oldwei.netty.client.util.FileUtil;
import top.oldwei.netty.common.codec.PacketDecoder;
import top.oldwei.netty.common.codec.PacketEncoder;
import top.oldwei.netty.common.packet.FileTransferPacketV1;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author:weizd
 * @Date:20-5-12
 */

@Slf4j
@Component
public class NettyInit implements CommandLineRunner {


    @Resource
    private FileUtil fileUtil;

    @Value("${oldwei.netty.port}")
    private Integer port;

    @Override
    public void run(String... args) throws Exception {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {

                        // 拆包粘包处理
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));

                        // 自定义
                        ch.pipeline().addLast(new PacketDecoder());





                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });
        // 4.建立连接
        bootstrap.connect("127.0.0.1", port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接成功!");

                Channel channel = ((ChannelFuture)future).channel();


                String filePath = "/home/weizd/dataset/dataset/air_nohead.csv";

                filePath = "/home/weizd/baseEnvironment/test.zip";

                File file = new File(filePath);



                long s = SystemClock.now();

                String md5 = fileUtil.getFileMd5(filePath);

                RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");

                byte[] bytes = new byte[1024];
                int readLength;
                int startIndex = 0;
                FileTransferPacketV1 fileTransferPacketV1 = null;
                while ((readLength = randomAccessFile.read(bytes)) != -1){
                    fileTransferPacketV1 = new FileTransferPacketV1();

                    if(readLength < 1024){
                        byte[] copy = new byte[readLength];
                        System.arraycopy(bytes, 0, copy, 0, readLength);
                        fileTransferPacketV1.setBytes(copy);
                    }else {
                        fileTransferPacketV1.setBytes(bytes);
                    }
                    fileTransferPacketV1.setFileName(file.getName());
                    fileTransferPacketV1.setFilePath(filePath);

                    fileTransferPacketV1.setStartPos(startIndex);
                    fileTransferPacketV1.setEndPos(startIndex+readLength);
                    fileTransferPacketV1.setMd5(md5);
                    channel.writeAndFlush(fileTransferPacketV1);

                    startIndex = startIndex + readLength;

                }
                randomAccessFile.close();


                log.info("fileSize: {}, send success take: {}",file.length(),SystemClock.now() -s);

            } else {
                log.info("连接失败!");
            }
        });



    }




}
