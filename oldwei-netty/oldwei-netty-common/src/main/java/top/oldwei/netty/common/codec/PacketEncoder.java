package top.oldwei.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.packet.FileTransferPacketV1;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
@Slf4j
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        if(packet instanceof FileTransferPacketV1){
            FileTransferPacketV1 fileTransferPacketV1 = (FileTransferPacketV1)packet;
            log.info("packetEncoder---> {} - >{}", fileTransferPacketV1.getStartPos(),fileTransferPacketV1.getEndPos());
        }

        PacketCodeC.INSTANCE.encode(packet,byteBuf);
    }
}




