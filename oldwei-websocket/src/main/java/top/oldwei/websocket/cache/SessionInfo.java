package top.oldwei.websocket.cache;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import top.oldwei.websocket.pojo.ChatSessionInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-24
 */
@Slf4j
public class SessionInfo {


    private static Map<String, Set<String>> groupSessionInfoMap = Maps.newConcurrentMap();


    private static Map<String, ChatSessionInfo> sessionInfoMap = Maps.newConcurrentMap();


    public static ChatSessionInfo getChatSessionInfo(String name){
        return sessionInfoMap.get(name);
    }

    public static void setChatSessionInfo(String name,ChatSessionInfo chatSessionInfo){
        sessionInfoMap.put(name,chatSessionInfo);
    }

    public static Map<String,ChatSessionInfo> getSessionInfoMap(){
        return sessionInfoMap;
    }

    public static Set<String> getGroupUser(String groupId){
        return groupSessionInfoMap.get(groupId);
    }

    public static void joinGroup(String groupId,String username){
        if(groupSessionInfoMap.containsKey(groupId)){
            groupSessionInfoMap.get(groupId).add(username);
        }else {
            HashSet<String> userSet = Sets.newHashSet();
            userSet.add(username);
            groupSessionInfoMap.put(groupId,userSet);
        }
    }

    public static void leaveGroup(String groupId,String username){
        if(groupSessionInfoMap.containsKey(groupId)){
            groupSessionInfoMap.get(groupId).remove(username);
        }
    }



}
