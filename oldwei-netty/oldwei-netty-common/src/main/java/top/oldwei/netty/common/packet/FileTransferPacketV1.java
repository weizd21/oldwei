package top.oldwei.netty.common.packet;

import lombok.Data;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.constant.Command;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
@Data
public class FileTransferPacketV1 extends Packet {

    /**
     * 文件的md5值
     */
    private String md5;

    private String filePath;

    private String fileName;

    private Integer startPos;

    private Integer endPos;

    private byte[] bytes;


    @Override
    public Byte getCommand() {
        return Command.FILE_TRANSFER;
    }

}
