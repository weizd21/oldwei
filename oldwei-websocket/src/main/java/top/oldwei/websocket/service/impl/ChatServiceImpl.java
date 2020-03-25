package top.oldwei.websocket.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.cache.SessionInfo;
import top.oldwei.websocket.constant.OperateConstant;
import top.oldwei.websocket.constant.WebSocketConstant;
import top.oldwei.websocket.pojo.ChatData;
import top.oldwei.websocket.pojo.ChatSessionInfo;
import top.oldwei.websocket.service.ChatService;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Override
    public void dealMessage(WebSocketSession webSocketSession, String message) {
        ChatData chatData = JSONObject.parseObject(message,ChatData.class);
        log.info("chatData:【{}】",chatData);
        if(StrUtil.isNotEmpty(chatData.getOperate())){
            if(OperateConstant.JOIN_GROUP.equals(chatData.getOperate())){

                // 加入群组
                SessionInfo.joinGroup(chatData.getGroupId(),chatData.getFromName());


                ChatData chatData1 = new ChatData();
                chatData1.setMsg("["+webSocketSession.getAttributes().get(WebSocketConstant.USER_ID)+"]");
                sendGroupMessage(chatData.getGroupId(),JSONObject.toJSON(chatData1).toString().getBytes());


            }else if(OperateConstant.SINGLE_MESSAGE.equals(chatData.getOperate())){
                // 私聊


            }else if(OperateConstant.GROUP_MESSAGE.equals(chatData.getOperate())){
                // 群聊
                for(Map.Entry<String,ChatSessionInfo> entry:SessionInfo.getSessionInfoMap().entrySet()){
                    try {
                        sendMessage(entry.getValue().getWebSocketSession(),JSONObject.toJSON(chatData).toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }else if(OperateConstant.LEAVE_GROUP.equals(chatData.getOperate())){
                // 退出群组


            }else {
                log.info("---> 暂不支持的命令");
            }




        }





    }

    private void sendGroupMessage(String groupId,byte[] bytes){
        Set<String> userSet = SessionInfo.getGroupUser(groupId);
        if( userSet != null ){
            for (String username:userSet){
                ChatSessionInfo chatSessionInfo = SessionInfo.getChatSessionInfo(username);
                if(chatSessionInfo != null){
                    WebSocketSession webSocketSession = chatSessionInfo.getWebSocketSession();
                    if(webSocketSession != null){
                        try {
                            sendMessage(webSocketSession,bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }




    }

    @Override
    public void sendMessage(WebSocketSession webSocketSession, byte[] bytes) throws IOException {
        webSocketSession.sendMessage(new TextMessage(bytes));
    }

    @Override
    public void close(WebSocketSession webSocketSession) {

    }
}
