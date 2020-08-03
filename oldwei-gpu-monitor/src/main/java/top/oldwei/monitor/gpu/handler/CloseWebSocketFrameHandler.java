package top.oldwei.monitor.gpu.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import static top.oldwei.monitor.gpu.util.CacheUtil.sessionMap;

/**
 * @Author:weizd
 * @Date:20-8-3
 */
@Slf4j
public class CloseWebSocketFrameHandler extends SimpleChannelInboundHandler<CloseWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CloseWebSocketFrame closeWebSocketFrame) throws Exception {

        String channelIdStr = channelHandlerContext.channel().id().asShortText();
        sessionMap.remove(channelIdStr);
        log.info("[{}] close success ",channelIdStr);
    }
}
