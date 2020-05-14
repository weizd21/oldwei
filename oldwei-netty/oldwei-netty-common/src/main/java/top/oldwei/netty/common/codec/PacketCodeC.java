package top.oldwei.netty.common.codec;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.constant.Base;
import top.oldwei.netty.common.constant.Command;
import top.oldwei.netty.common.constant.SerializerType;
import top.oldwei.netty.common.packet.FileTransferPacketV1;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.LoginRequestPacket;
import top.oldwei.netty.common.serializer.Serializer;

/**
 * @Author:weizd
 * @Date:20-5-12
 */
@Slf4j
public class PacketCodeC {

    public final static PacketCodeC INSTANCE = new PacketCodeC();



    public void encode(Packet packet, ByteBuf byteBuf){
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 魔法数据 代表收到的数据是约定的内容
        byteBuf.writeInt(Base.MAGIC_NUMBER);
        // 版本
        byteBuf.writeByte(packet.getVersion());
        // 序列化 类型 标识
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerType());
        // 命令
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);


        byteBuf.writeBytes(bytes);

    }


    public Packet decode(ByteBuf byteBuf){
        // 跳过magic number
//        byteBuf.skipBytes(4);
        int magicNumber = byteBuf.readInt();
        // 跳过版本号
//        byteBuf.skipBytes(1);
        Byte version = byteBuf.readByte();
        // 序列化算法标识
        byte serializeType = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);

        Serializer serializer = getSerializer(serializeType);

        if(null != requestType && null != serializer){
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }


    private Class<? extends Packet> getRequestType(byte command){
        if(command == Command.LOGIN_REQUEST){
            return LoginRequestPacket.class;
        }else if(command == Command.FILE_TRANSFER){
            return FileTransferPacketV1.class;
        }else if(command == Command.FILE_TRANSFER_REQUEST){
            return FileTransferRequestPacket.class;
        }else if(command == Command.FILE_TRANSFER_RESPONSE){
            return FileTransferResponsePacket.class;
        }
        return null;
    }




    private Serializer getSerializer(byte serializerType){
        if(serializerType == SerializerType.JSON){
            return Serializer.DEFAULT;
        }
        return Serializer.DEFAULT;
    }


}
