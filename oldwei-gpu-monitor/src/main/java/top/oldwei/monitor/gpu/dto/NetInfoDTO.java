package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-18
 * 设备网络信息
 *
 */
@Data
public class NetInfoDTO implements Serializable {

    private String face;

    private NetItemInfo receive;

    private NetItemInfo transmit;

}
