package top.oldwei.websocket.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-26
 */
@Data
public class UserGroupDTO implements Serializable {

    // 用户名、群组名
    private String name;
    // 用户id或者群组id
    private String id;
    // group_message 或者 single_message
    private String type;
    // 最新的消息
    private String msg;


}
