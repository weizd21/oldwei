package top.oldwei.websocket.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-21
 */
@Data
public class ChatDataReqDTO implements Serializable {



    /**
     * 发送人id
     */
    private String fromId;

    /**
     * 接收人
     */
    private String toId;

    /**
     * 操作命令
     */
    private String operate;
    /**
     * 分组id
     */
    private String groupId;

    /**
     * 消息
     */
    private String msg;


}
