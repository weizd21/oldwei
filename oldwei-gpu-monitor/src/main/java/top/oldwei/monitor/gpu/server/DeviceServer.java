package top.oldwei.monitor.gpu.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import top.oldwei.monitor.gpu.code.MessageDecoder;
import top.oldwei.monitor.gpu.handler.CloseWebSocketFrameHandler;
import top.oldwei.monitor.gpu.handler.FullHttpRequestHandler;
import top.oldwei.monitor.gpu.handler.ObjectHandler;
import top.oldwei.monitor.gpu.handler.TextWebSocketFrameHandler;

/**
 * @Author:weizd
 * @Date:20-8-3
 */
@Slf4j
@Component
public class DeviceServer implements CommandLineRunner {


    @Value("${oldwei.deviceServer.port:8017}")
    private int port;


    @Override
    public void run(String... args) throws Exception {
        log.info("enter DeviceServer ");
        this.run();
        log.info("netty 监听 {} 成功",port);
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

                        log.info("收到新的连接----->");

                        ch.pipeline().addLast(new MessageDecoder());


                        // http请求解码器 将请求和应答消息编码或者解码为HTTP消息
                        ch.pipeline().addLast(new HttpServerCodec());

                        //以块的方式来写的处理器 主要作用是支持异步发送大的码流(例如大文件传输),但不占用过多的内存,防止JAVA内存溢出
                        ch.pipeline().addLast(new ChunkedWriteHandler());
                        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
                        ch.pipeline().addLast(new HttpObjectAggregator(8192));
                        ch.pipeline().addLast(new FullHttpRequestHandler());



                        // 自定义的处理器
                        ch.pipeline().addLast(new TextWebSocketFrameHandler());

                        ch.pipeline().addLast(new CloseWebSocketFrameHandler());

                        ch.pipeline().addLast(new ObjectHandler());


                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/device-info", null, true, 65536 * 10));


                    }
                });
        serverBootstrap.bind(port);
    }

}
