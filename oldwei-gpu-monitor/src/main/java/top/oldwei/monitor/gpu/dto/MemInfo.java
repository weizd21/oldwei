package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-19
 *
 * 内存信息
 *
 */
@Data
public class MemInfo implements Serializable {

    private String total;

    private String used;

    private String free;

    private String shared;

    private String buffCache;

    private String available;









}
