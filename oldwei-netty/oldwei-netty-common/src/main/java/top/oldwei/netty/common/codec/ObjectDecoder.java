package top.oldwei.netty.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.constant.Base;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-22
 */
@Slf4j
public class ObjectDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final int magic1 = byteBuf.getUnsignedByte(byteBuf.readerIndex());
        final int magic2 = byteBuf.getUnsignedByte(byteBuf.readerIndex() + 1);

        log.info("base magic_number: {}",Base.MAGIC_NUMBER);

        ByteArrayOutputStream bo = new ByteArrayOutputStream();

        byte[] tmp = new byte[4];

        byteBuf.readBytes(tmp);

        bo.write(tmp);

        log.info("tmp: {}",bo.toString());


        log.info("magic2: {}",magic2);
        log.info("magic1 :{},magic1 == Base.MAGIC_NUMBER : {}, magic1 == 'G' : {}",magic1,magic1 == Base.MAGIC_NUMBER,magic1 == 'G');



        channelHandlerContext.pipeline().remove(this);

    }
}
