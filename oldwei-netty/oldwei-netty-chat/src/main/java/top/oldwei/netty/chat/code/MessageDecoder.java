package top.oldwei.netty.chat.code;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-4-13
 */
@Slf4j
public class MessageDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame s, List<Object> list) throws Exception {
        log.info("---------------->");
        log.info(s.text());
        log.info("---------------->");
    }
}
