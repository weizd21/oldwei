package top.oldwei.websocket.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-21
 */
@Data
public class WebSSHData implements Serializable {

    private String operate;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String command ;

}
