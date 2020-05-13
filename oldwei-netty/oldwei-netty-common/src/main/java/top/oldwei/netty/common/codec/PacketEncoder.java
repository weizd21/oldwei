package top.oldwei.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.base.Packet;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
@Slf4j
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        log.debug("packetEncoder--->");
        PacketCodeC.INSTANCE.encode(packet,byteBuf);
    }
}




