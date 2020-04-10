package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-19
 */
@Data
public class MemoryInfoDTO implements Serializable {

    private MemInfo memInfo;

    private SwapInfo swapInfo;

}
