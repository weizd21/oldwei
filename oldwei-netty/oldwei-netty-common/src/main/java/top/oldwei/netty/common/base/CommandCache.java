package top.oldwei.netty.common.base;

import com.google.common.collect.Maps;
import top.oldwei.netty.common.constant.Command;
import top.oldwei.netty.common.packet.FileTransferPacketV1;
import top.oldwei.netty.common.packet.FileTransferRequestPacket;
import top.oldwei.netty.common.packet.FileTransferResponsePacket;
import top.oldwei.netty.common.packet.StartFileTransferRequestPacket;
import top.oldwei.netty.common.packet.StartFolderTransferRequestPacket;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
public class CommandCache {
    public static Map<Byte,Class<? extends Packet>> commandInfo = Maps.newConcurrentMap();


    static {
        // 文件传输
        commandInfo.put(Command.FILE_TRANSFER, FileTransferPacketV1.class);
        // 文件传输中 请求
        commandInfo.put(Command.FILE_TRANSFER_REQUEST,FileTransferRequestPacket.class);
        // 文件传输中 响应
        commandInfo.put(Command.FILE_TRANSFER_RESPONSE, FileTransferResponsePacket.class);
        // 开始文件传输 请求
        commandInfo.put(Command.START_FILE_TRANSFER_REQUEST, StartFileTransferRequestPacket.class);
        // 开始文件夹传输 请求
        commandInfo.put(Command.START_FOLDER_TRANSFER_REQUEST, StartFolderTransferRequestPacket.class);
    }

}
