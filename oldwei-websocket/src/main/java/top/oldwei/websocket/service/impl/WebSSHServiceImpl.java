package top.oldwei.websocket.service.impl;

import com.google.common.collect.Maps;
import com.jcraft.jsch.JSch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.constant.WebSocketConstant;
import top.oldwei.websocket.pojo.SSHConnectInfo;
import top.oldwei.websocket.service.WebSSHService;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Slf4j
@Service
public class WebSSHServiceImpl implements WebSSHService {

    private static Map<String,SSHConnectInfo> sessionMap = Maps.newConcurrentMap();


    @Override
    public void initConnection(WebSocketSession webSocketSession) {
        JSch jSch = new JSch();
        SSHConnectInfo sshConnectInfo = new SSHConnectInfo();
        sshConnectInfo.setJSch(jSch);
        sshConnectInfo.setWebSocketSession(webSocketSession);
        String uuid = String.valueOf(webSocketSession.getAttributes().get(WebSocketConstant.USER_UUID_KEY));
        sessionMap.put(uuid,sshConnectInfo);
        log.info("【{}】初始化连接成功。。。",uuid);
    }

    @Override
    public void dealMessage(String buffer, WebSocketSession webSocketSession) {

    }

    @Override
    public void sendMessage(WebSocketSession webSocketSession, byte[] bytes) {

    }

    @Override
    public void close(WebSocketSession webSocketSession) {

    }
}
