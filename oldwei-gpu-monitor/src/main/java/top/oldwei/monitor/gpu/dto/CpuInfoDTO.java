package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-19
 */
@Data
public class CpuInfoDTO implements Serializable {

    private String processor;

    private String vendorId;

    private String cpuFamily;

    private String model;

    private String modelName;

    private String stepping;

    private String microcode;

    private String cpuMHz;

    private String cacheSize;

    private String physicalId;

    private String siblings;

    private String coreId;

    private String cpuCores;

    private String apicid;

    private String initialApicid;

    private String fpu;

    private String fpuException;

    private String cpuidLevel;

    private String wp;

    private String flags;

    private String bugs;

    private String bogomips;

    private String clflushSize;

    private String cacheAlignment;

    private String addressSizes;

    private String powerManagement;

}
