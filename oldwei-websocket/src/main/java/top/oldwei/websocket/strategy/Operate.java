package top.oldwei.websocket.strategy;

import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.pojo.ChatData;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
public interface Operate {
    void execute(WebSocketSession webSocketSession, ChatData chatData);
}
