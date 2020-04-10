package top.oldwei.netty.chat.init;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.oldwei.netty.chat.handler.WebSocketHandler;

/**
 * @Author:weizd
 * @Date:20-4-9
 */
@Component
@Slf4j
public class NettyServerInit implements CommandLineRunner {

    @Value("${oldwei.netty.port}")
    private Integer port;

    @Override
    public void run(String... args) throws Exception {
        log.info("启动netty服务端监听");
        this.run();
    }

    private void run(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // ch.pipeline().addLast(new ServerHandler());
                        // 拆包粘包 同时过滤非约定协议的 通信
                    /*
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        ch.pipeline().addLast(new GroupMessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    */
                        // http请求解码器
                        ch.pipeline().addLast(new HttpServerCodec());
                        // 自定义处理器
                        ch.pipeline().addLast(new WebSocketHandler());
                    }
                });
        serverBootstrap.bind(port);
    }



}
