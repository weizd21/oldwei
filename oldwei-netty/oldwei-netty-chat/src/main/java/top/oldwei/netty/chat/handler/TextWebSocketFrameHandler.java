package top.oldwei.netty.chat.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import static top.oldwei.netty.chat.cache.SessionInfo.*;

/**
 * @Author:weizd
 * @Date:20-4-9
 */
@Slf4j
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.info("TextWebSocketFrameHandler---channelRead0");

        ChannelId channelId = channelHandlerContext.channel().id();

        log.info("ChannelId : 【{}】",channelId);

        log.info(textWebSocketFrame.text());

        log.info(JSONObject.toJSON(textWebSocketFrame).toString());


        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString("{\"finalFragment\":true}")));


    }



}
