package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 *  nvidia-smi --query-gpu=index,uuid,name,utilization.gpu,utilization.memory,memory.total,memory.used,memory.free,temperature.gpu,temperature.memory --format=csv,noheader,nounits
 *
 */

/**
 * @Author:weizd
 * @Date:20-3-18
 */
@Data
public class GpuInfoDTO implements Serializable {

    /**
     * 编号
     */
    private String index;
    /**
     * 唯一性值
     */
    private String uuid;
    /**
     * 名称
     */
    private String name;
    /**
     * GPU使用率
     */
    private String utilizationGpu;
    /**
     * 显存使用率
     */
    private String utilizationMemory;
    /**
     * 总显存
     */
    private String memoryTotal;
    /**
     * 已使用显存
     */
    private String memoryUsed;
    /**
     * 空闲显存
     */
    private String memoryFree;
    /**
     * GPU温度
     */
    private String temperatureGpu;
    /**
     * 显存温度
     */
    private String temperatureMemory;



}
