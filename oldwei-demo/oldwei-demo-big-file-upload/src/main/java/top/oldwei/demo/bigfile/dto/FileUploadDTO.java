package top.oldwei.demo.bigfile.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-1-16
 */
@Data
@ToString
public class FileUploadDTO implements Serializable {

    /**
     * 文件块编号
     */
    private Integer chunkNumber;
    /**
     * 分块大小
     */
    private Long chunkSize;
    /**
     * 当前块大小
     */
    private Long currentChunkSize;
    /**
     * 总大小
     */
    private Long totalSize;
    /**
     *
     */
    private String identifier;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 相对路径
     */
    private String relativePath;
    /**
     * 总块数
     */
    private Integer totalChunks;


}
