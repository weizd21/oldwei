package top.oldwei.websocket.pojo;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Data
public class SSHConnectInfo implements Serializable {

    private WebSocketSession webSocketSession;

    private JSch jSch;

    private Channel channel;

}
