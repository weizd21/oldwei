package top.oldwei.websocket.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Data
public class ChatDataResDTO implements Serializable {

    /**
     * 返回数据类型
     */
    private String resType;

    private Object data;

}
