package top.oldwei.netty.common.packet;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.netty.common.base.Packet;
import top.oldwei.netty.common.constant.Command;
import top.oldwei.netty.common.domain.FileInfo;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Slf4j
@Data
public class StartFolderTransferRequestPacket extends Packet {

    /**
     * 要传输的文件夹路径
     * 将此文件夹路径下的所有文件 都进行传输
     */
    private String basePath;

    /**
     * 要传输的文件列表
     */
    List<FileInfo> fileList = Lists.newArrayList();


    @Override
    public Byte getCommand() {
        return Command.START_FOLDER_TRANSFER_REQUEST;
    }
}
