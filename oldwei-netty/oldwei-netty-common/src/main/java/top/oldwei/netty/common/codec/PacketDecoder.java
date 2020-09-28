package top.oldwei.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-13
 *
 *  解码器
 *
 *
 *
 */
@Slf4j
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.debug("packetDecoder--->");
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
