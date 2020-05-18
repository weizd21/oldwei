package top.oldwei.netty.server.handler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:weizd
 * @Date:20-4-10
 */
@Slf4j
public class FullHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private String webSocketUrl="ws://127.0.0.1:8013/chat";

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        log.info("fullHttpRequest----> ");

        log.info(JSONObject.toJSON(fullHttpRequest).toString());

        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory
                = new WebSocketServerHandshakerFactory(webSocketUrl, null, false);

        Channel connectChannel = channelHandlerContext.channel();

        WebSocketServerHandshaker socketServerHandShaker = wsFactory.newHandshaker(fullHttpRequest);
        if (socketServerHandShaker == null) {
            // 发送版本错误
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(connectChannel);
        } else {
            // 握手响应
            socketServerHandShaker.handshake(connectChannel, fullHttpRequest);

//            log.info(connectChannel.localAddress().toString());
//            log.info(connectChannel.remoteAddress().toString());

            ChannelId channelId = connectChannel.id();

            String token = getToken(fullHttpRequest.uri());


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

}
