package top.oldwei.websocket.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import top.oldwei.websocket.cache.SessionInfo;
import top.oldwei.websocket.constant.OperateConstant;
import top.oldwei.websocket.constant.ResTypeConstant;
import top.oldwei.websocket.constant.WebSocketConstant;
import top.oldwei.websocket.dto.ChatDataResDTO;
import top.oldwei.websocket.dto.UserDTO;
import top.oldwei.websocket.dto.UserGroupDTO;
import top.oldwei.websocket.entity.Group;
import top.oldwei.websocket.entity.User;
import top.oldwei.websocket.mapper.GroupMapper;
import top.oldwei.websocket.pojo.ChatData;
import top.oldwei.websocket.pojo.ChatSessionInfo;
import top.oldwei.websocket.service.ChatService;
import top.oldwei.websocket.service.GroupService;
import top.oldwei.websocket.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    // 存放 组id - 用户id列表 键值对信息
    private static Map<String, Set<String>> groupInfoMap = Maps.newConcurrentMap();


    // 存放 用户id-{webSocketSession,Set<Group>,loginTime}键值对信息
    private static Map<String, ChatSessionInfo> userInfoMap = Maps.newConcurrentMap();

    /**
     * 初始化 websocket 连接信息
     * 广播通知 上线信息
     * 发送 在线用户列表 信息
     * @param webSocketSession
     */
    @Override
    public void initConnection(WebSocketSession webSocketSession) {

        // 初始化连接信息
        String userId = webSocketSession.getAttributes().get(WebSocketConstant.USER_ID).toString();
        ChatSessionInfo chatSessionInfo = new ChatSessionInfo();
        chatSessionInfo.setWebSocketSession(webSocketSession);
        chatSessionInfo.setLoginTime(LocalDateTime.now());
        // 查询用户组信息

        List<Group> groups = groupService.listGroupByUserId(userId);
        log.info("用户组信息:{}",groups);

        userInfoMap.put(userId,chatSessionInfo);


        // 查询用户详细信息
        User user = userService.getById(userId);


        // 广播信息
        ChatDataResDTO chatDataResDTO = new ChatDataResDTO();
        chatDataResDTO.setData("【"+user.getUsername()+ "】 上线了");
        chatDataResDTO.setResType(ResTypeConstant.MSG_ALL);
        log.info("广播消息：{}",chatDataResDTO);
        broadcast(JSONObject.toJSON(chatDataResDTO).toString().getBytes());


        // 更新在线用户信息
        List<User> users = userService.listByIds(userInfoMap.keySet());
        List<UserGroupDTO> userGroupDTOS = Lists.newArrayList();
        UserGroupDTO userGroupDTO = null;
        for(User u : users){
            userGroupDTO = new UserGroupDTO();
            userGroupDTO.setName(u.getUsername());
            userGroupDTO.setId(u.getId());
            userGroupDTOS.add(userGroupDTO);
        }

        log.info("在线用户信息：{}",users);
        ChatDataResDTO infoUser = new ChatDataResDTO();
        infoUser.setResType(ResTypeConstant.INFO_USER);
        infoUser.setData(userGroupDTOS);
        log.info("广播信息:{}",infoUser);
        broadcast(JSONObject.toJSON(infoUser).toString().getBytes());
    }


    /**
     * 广播消息
     * 发送消息给所有的在线用户
     * @param bytes
     */
    public void broadcast(byte[] bytes){
        for(Map.Entry<String,ChatSessionInfo> entry:userInfoMap.entrySet()){
            try {
                sendMessage(entry.getValue().getWebSocketSession(),bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }




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


    /**
     * 对指定群组 里面的在线用户 进行消息推送
     * @param groupId
     * @param bytes
     */
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
        userInfoMap.remove(webSocketSession.getAttributes().get(WebSocketConstant.USER_ID));
        try {
            webSocketSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
