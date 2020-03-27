package top.oldwei.websocket.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-21
 */
@Data
public class ChatData implements Serializable {

    /**
     * 发送人id
     */
    private String fromId;
    /**
     * 发送人名称
     */
    private String fromName;
    /**
     * 接收人 或者 群组
     */
    private String toId;

    /**
     * 操作命令
     */
    private String operate;


    /**
     * 消息
     */
    private String msg;


}
