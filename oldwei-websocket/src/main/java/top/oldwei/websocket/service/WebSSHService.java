package top.oldwei.websocket.service;

import org.springframework.web.socket.WebSocketSession;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
public interface WebSSHService {

    /**
     * 初始化连接
     * @param webSocketSession
     */
    void initConnection(WebSocketSession webSocketSession);

    /**
     * 处理接收到的消息
     * @param buffer
     * @param webSocketSession
     */
    void dealMessage(String buffer,WebSocketSession webSocketSession);

    /**
     * 发送消息到客户端
     * @param webSocketSession
     * @param bytes
     */
    void sendMessage(WebSocketSession webSocketSession,byte[] bytes);


    /**
     * 关闭连接
     * @param webSocketSession
     */
    void close(WebSocketSession webSocketSession);

}
