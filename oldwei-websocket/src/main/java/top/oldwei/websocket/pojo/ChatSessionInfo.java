package top.oldwei.websocket.pojo;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
@Data
public class ChatSessionInfo implements Serializable {

    private WebSocketSession webSocketSession;

    private LocalDateTime loginTime;

    private Set<String> groupIds = new HashSet<>();
}
