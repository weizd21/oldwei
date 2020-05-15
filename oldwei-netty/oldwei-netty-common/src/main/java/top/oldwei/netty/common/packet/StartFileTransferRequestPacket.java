package top.oldwei.netty.common.packet;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.constant.Command;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Slf4j
@Data
public class StartFileTransferRequestPacket extends Packet {

    private String md5;

    private long fileSize;

    private String fileName;

    private String filePath;



    @Override
    public Byte getCommand() {
        return Command.START_FILE_TRANSFER_REQUEST;
    }
}
