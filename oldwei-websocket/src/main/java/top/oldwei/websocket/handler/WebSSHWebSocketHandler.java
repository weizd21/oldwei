package top.oldwei.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.constant.WebSocketConstant;
import top.oldwei.websocket.service.WebSSHService;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Slf4j
@Component
public class WebSSHWebSocketHandler implements WebSocketHandler {

    @Autowired
    private WebSSHService webSSHService;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("连接webSocket成功的回调");
        webSSHService.initConnection(webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("收到客户端[{}]的信息[{}]", String.valueOf(webSocketSession.getAttributes().get(WebSocketConstant.USER_UUID_KEY)),JSONObject.toJSONString(webSocketMessage));
        if(webSocketMessage instanceof TextMessage){
            webSSHService.dealMessage(webSocketSession,((TextMessage) webSocketMessage).getPayload());
        }else if(webSocketMessage instanceof BinaryMessage){

        }else if(webSocketMessage instanceof PongMessage){

        }else {
            log.info("暂不支持的消息格式");
        }

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        log.error("数据传输错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("断开webSocket连接");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
