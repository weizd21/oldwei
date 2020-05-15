package top.oldwei.netty.server.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Data
public class TransferFileInfo implements Serializable {

    /**
     * 总字节数
     */
    private long fileSize;

    /**
     * 已经传输的 索引位置 顺序传输,此值 也是已经传输的文件大小
     */
    private long transferPos;

    /**
     * 源文件路径,文件在客户端时所在的路径
     */
    private String sourceFilePath;

    /**
     * 当前文件存放的路径
     */
    private String currentFilePath;


}
