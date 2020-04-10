package top.oldwei.websocket.interceptor;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import top.oldwei.websocket.constant.WebSocketConstant;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Slf4j
public class ChatInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("ChatInterceptor beforeHandshake...");
        if(serverHttpRequest instanceof ServletServerHttpRequest){
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            String token = request.getURI().toString().split("token=")[1];
            log.info("token:[{}]",token);
            map.put(WebSocketConstant.USER_ID, token);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("ChatInterceptor afterHandshake...");
    }
}
