package top.oldwei.netty.server.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.oldwei.netty.common.codec.PacketDecoder;
import top.oldwei.netty.common.codec.PacketEncoder;
import top.oldwei.netty.server.handler.FileTransferRequestHander;
import top.oldwei.netty.server.handler.FileTransferV1Hander;

/**
 * @Author:weizd
 * @Date:20-5-12
 */
@Slf4j
@Component
public class NettyInit implements CommandLineRunner {

    @Value("${oldwei.netty.port}")
    private Integer port;


    @Override
    public void run(String... args) throws Exception {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        log.info("收到新的连接----->");

                        // 拆包粘包处理
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        // 自定义
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new FileTransferV1Hander());
                        ch.pipeline().addLast(new FileTransferRequestHander());
                        ch.pipeline().addLast(new PacketEncoder());


                        // http请求解码器 将请求和应答消息编码或者解码为HTTP消息
                        ch.pipeline().addLast(new HttpServerCodec());
                        //以块的方式来写的处理器 主要作用是支持异步发送大的码流(例如大文件传输),但不占用过多的内存,防止JAVA内存溢出
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
                        ch.pipeline().addLast(new HttpObjectAggregator(8192));
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/chat", null, true, 65536 * 10));


                    }
                });
        serverBootstrap.bind(port);
    }
}


