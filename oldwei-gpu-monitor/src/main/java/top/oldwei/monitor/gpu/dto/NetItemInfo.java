package top.oldwei.monitor.gpu.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-18
 */
@Data
public class NetItemInfo implements Serializable {

    private String bytes;

    private String packets;

    private String errs;

    private String drop;

    private String fifo;

    private String frame;

    private String compressed;

    private String multicast;

}
