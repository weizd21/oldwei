package top.oldwei.netty.server.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;

/**
 * @Author:weizd
 * @Date:20-4-10
 */
@Slf4j
public class FullHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    /**
     * 2020.05.21 这个值没有什么屌用
     */
    private String webSocketUrl="ws://127.0.0.1:8013/ws";


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        log.info("fullHttpRequest----> ");

        log.info(JSONObject.toJSON(fullHttpRequest).toString());

        log.info("headers: {}",fullHttpRequest.headers());

        for(Map.Entry<String,String> header:fullHttpRequest.headers()){
            log.info("{}:{}",header.getKey(),header.getValue());
        }

        if(!fullHttpRequest.decoderResult().isSuccess() && !HttpHeaderValues.WEBSOCKET.equals(fullHttpRequest.headers().get("Upgrade"))){

            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST);

            ByteBuf byteBuf = Unpooled.copiedBuffer(fullHttpResponse.status().toString(),CharsetUtil.UTF_8);
            fullHttpResponse.content().writeBytes(byteBuf);
            byteBuf.release();
            channelHandlerContext.channel().writeAndFlush(fullHttpResponse);

            return;
        }

        String token = getToken(fullHttpRequest.uri());

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory
                = new WebSocketServerHandshakerFactory(webSocketUrl, null, false);

        Channel connectChannel = channelHandlerContext.channel();

        WebSocketServerHandshaker socketServerHandShaker = wsFactory.newHandshaker(fullHttpRequest);
        if (socketServerHandShaker == null) {
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.NON_AUTHORITATIVE_INFORMATION);
            // 发送版本错误
            channelHandlerContext.channel().writeAndFlush(fullHttpResponse);
//            channelHandlerContext.channel().close();
//            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(connectChannel);

        } else {
            // 握手响应
            socketServerHandShaker.handshake(connectChannel, fullHttpRequest);

            ChannelId channelId = connectChannel.id();

//            // 登录成功
//            userMap.put(token,channelId);
//
//            channelGroup.add(connectChannel);

            log.info("用户:【{}】,ChannelId:【{}】上线成功",token,channelId);

        }

    }

    /**
     * 从uri中获取TOKEN信息
     * @param uri
     * @return
     */
    private String getToken(String uri){
        String token = "";
        if(StrUtil.isNotEmpty(uri)){
            if(uri.contains("=")){
                token = uri.split("=")[1];
            }
        }
        return token;
    }




    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        //返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            setContentLength(res, res.content().readableBytes());
        }
        //如果是非Keep-alive,关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }

    }

}
