package top.oldwei.monitor.gpu.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:weizd
 * @Date:20-4-10
 */
@Slf4j
public class ObjectHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info(o.getClass().getName());
        log.info("object..............");
    }
}
