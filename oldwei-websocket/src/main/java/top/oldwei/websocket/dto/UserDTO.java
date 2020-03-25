package top.oldwei.websocket.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Data
public class UserDTO implements Serializable {

    private String id;

    private String userCode;

    private String username;

    private String password;

}
