package top.oldwei.netty.common.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
@Data
public class FileInfo implements Serializable {

    private String fileName;

    private String filePath;

    private String md5;

    private long fileSize;
    /**
     * 相对路径
     */
    private String relativePath;
}
