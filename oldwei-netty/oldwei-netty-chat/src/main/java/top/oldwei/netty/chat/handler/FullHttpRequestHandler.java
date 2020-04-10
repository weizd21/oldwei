package top.oldwei.netty.chat.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.Constant;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:weizd
 * @Date:20-4-10
 */
@Slf4j
public class FullHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private String webSocketUrl="ws://127.0.0.1:8010/chat";

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        log.info("fullHttpRequest----> ");

        log.info(JSONObject.toJSON(fullHttpRequest).toString());


        // 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory
                = new WebSocketServerHandshakerFactory(webSocketUrl, null, false);
        // region 从连接路径中截取连接用户名
        String uri = fullHttpRequest.uri();
        int i = uri.lastIndexOf("/");
        String userName = uri.substring(i + 1, uri.length());
        // endregion
        Channel connectChannel = channelHandlerContext.channel();

        WebSocketServerHandshaker socketServerHandShaker = wsFactory.newHandshaker(fullHttpRequest);
        if (socketServerHandShaker == null) {
            // 发送版本错误
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(connectChannel);
        } else {
            // 握手响应
            socketServerHandShaker.handshake(connectChannel, fullHttpRequest);
        }



    }
}
