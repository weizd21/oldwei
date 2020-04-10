package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-19
 */
@Data
public class SwapInfo implements Serializable {

    private String total;

    private String used;

    private String free;

}
