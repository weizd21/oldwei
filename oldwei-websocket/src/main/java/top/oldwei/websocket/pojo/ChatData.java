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
     * 发送人
     */
    private String fromName;
    /**
     * 接收人
     */
    private String toName;

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
