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
import top.oldwei.netty.common.codec.ObjectDecoder;
import top.oldwei.netty.common.codec.PacketDecoder;
import top.oldwei.netty.common.codec.PacketEncoder;
import top.oldwei.netty.server.codec.BaseDecoder;
import top.oldwei.netty.server.handler.FileTransferRequestHandler;
import top.oldwei.netty.server.handler.FileTransferV1Handler;
import top.oldwei.netty.server.handler.FullHttpRequestHandler;
import top.oldwei.netty.server.handler.ObjectHandler;
import top.oldwei.netty.server.handler.StartFileTransferRequestHandler;
import top.oldwei.netty.server.handler.StartFolderTransferRequestHandler;
import top.oldwei.netty.server.handler.TextWebSocketFrameHandler;

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
                        ch.pipeline().addLast(new BaseDecoder());
                    }
                });
        serverBootstrap.bind(port);
    }
}


