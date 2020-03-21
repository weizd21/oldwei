package top.oldwei.websocket.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import top.oldwei.websocket.handler.WebSSHWebSocketHandler;
import top.oldwei.websocket.interceptor.DeviceMonitorInterceptor;
import top.oldwei.websocket.interceptor.WebSSHInterceptor;
import top.oldwei.websocket.properties.Properties;


/**
 * @Author:weizd
 * @Date:20-3-19
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private Properties properties;

    @Autowired
    private WebSSHWebSocketHandler webSSHWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        log.info("注入webSocket处理器和拦截器...");

        log.info(JSONObject.toJSON(properties).toString());


        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry.addHandler(webSSHWebSocketHandler, "/webssh")
                .addInterceptors(new WebSSHInterceptor())
                .setAllowedOrigins("*");

        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry.addHandler(webSSHWebSocketHandler, "/device")
                .addInterceptors(new DeviceMonitorInterceptor())
                .setAllowedOrigins("*");

    }
}
