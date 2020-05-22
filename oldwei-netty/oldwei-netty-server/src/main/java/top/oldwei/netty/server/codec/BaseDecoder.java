package top.oldwei.netty.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.codec.PacketDecoder;
import top.oldwei.netty.common.codec.PacketEncoder;
import top.oldwei.netty.common.constant.Base;
import top.oldwei.netty.server.handler.FileTransferRequestHandler;
import top.oldwei.netty.server.handler.FileTransferV1Handler;
import top.oldwei.netty.server.handler.FullHttpRequestHandler;
import top.oldwei.netty.server.handler.StartFileTransferRequestHandler;
import top.oldwei.netty.server.handler.StartFolderTransferRequestHandler;
import top.oldwei.netty.server.handler.TextWebSocketFrameHandler;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-22
 *
 * 动态的控制 channelHandler 加载
 * 官方示例
 * https://netty.io/4.1/xref/io/netty/example/portunification/PortUnificationServerHandler.html
 *
 */
@Slf4j
public class BaseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        final int magic1 = byteBuf.getByte(byteBuf.readerIndex());
        final int magic2 = byteBuf.getByte(byteBuf.readerIndex()+1);

        final int magicW = byteBuf.getInt(0);

        if(isHttp(magic1,magic2)){
            switchToHttp(channelHandlerContext);
        }else if(isWProtocol(magicW)){
            // 自定义协议
            switchToWProtocol(channelHandlerContext);
        }

    }


    /**
     * 判断是否为http请求方式
     * @param magic1
     * @param magic2
     * @return
     */
    private static boolean isHttp(int magic1, int magic2) {
        return magic1 == 'G' && magic2 == 'E' || // GET
               magic1 == 'P' && magic2 == 'O' || // POST
               magic1 == 'P' && magic2 == 'U' || // PUT
               magic1 == 'H' && magic2 == 'E' || // HEAD
               magic1 == 'O' && magic2 == 'P' || // OPTIONS
               magic1 == 'P' && magic2 == 'A' || // PATCH
               magic1 == 'D' && magic2 == 'E' || // DELETE
               magic1 == 'T' && magic2 == 'R' || // TRACE
               magic1 == 'C' && magic2 == 'O';   // CONNECT
    }

    /**
     * 自定义协议
     * @param magic1
     * @return
     */
    private static boolean isWProtocol(int magic1){
        return magic1 == Base.MAGIC_NUMBER;
    }


    private void switchToHttp(ChannelHandlerContext channelHandlerContext){
        ChannelPipeline channelPipeline = channelHandlerContext.pipeline();
        // http请求解码器 将请求和应答消息编码或者解码为HTTP消息
        channelPipeline.addLast(new HttpServerCodec());
        //以块的方式来写的处理器 主要作用是支持异步发送大的码流(例如大文件传输),但不占用过多的内存,防止JAVA内存溢出
        channelPipeline.addLast(new ChunkedWriteHandler());
        // 将HTTP消息的多个部分组合成一条完整的HTTP消息;
        channelPipeline.addLast(new HttpObjectAggregator(8192));

        channelPipeline.addLast(new FullHttpRequestHandler());
        // 自定义的处理器
        channelPipeline.addLast(new TextWebSocketFrameHandler());

//      ch.pipeline().addLast(new WebSocketServerProtocolHandler("/chat", null, true, 65536 * 10));

        channelPipeline.remove(this);
    }

    private void switchToWProtocol(ChannelHandlerContext channelHandlerContext){
        ChannelPipeline channelPipeline = channelHandlerContext.pipeline();
        // 拆包粘包处理
        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
        // 自定义
        channelPipeline.addLast(new PacketDecoder());
        channelPipeline.addLast(new FileTransferV1Handler());
        channelPipeline.addLast(new FileTransferRequestHandler());
        channelPipeline.addLast(new StartFileTransferRequestHandler());
        channelPipeline.addLast(new StartFolderTransferRequestHandler());
        channelPipeline.addLast(new PacketEncoder());

        channelPipeline.remove(this);
    }


}
