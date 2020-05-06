package top.oldwei.netty.chat.cache;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:weizd
 * @Date:20-4-13
 */
public class SessionInfo {

    /**
     * 存储已经登录用户的channel对象
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 存储用户id和用户的channelId绑定
     */
    public static ConcurrentHashMap<String, ChannelId> userMap = new ConcurrentHashMap<>();
    /**
     * 用于存储群聊房间号和群聊成员的channel信息
     */
    public static ConcurrentHashMap<String, ChannelGroup> groupMap = new ConcurrentHashMap<>();



}
