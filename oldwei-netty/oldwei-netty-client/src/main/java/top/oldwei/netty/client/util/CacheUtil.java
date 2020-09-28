package top.oldwei.netty.client.util;

import cn.hutool.cache.Cache;
import com.google.common.collect.Maps;
import io.netty.channel.Channel;
import org.apache.tomcat.jni.FileInfo;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-5-14
 *
 * 存放 netty Channel 信息
 *
 *
 */
public class CacheUtil {

    public static Map<String, Channel> sessionMap = Maps.newConcurrentMap();


    public static Map<String,Long> takeTime = Maps.newConcurrentMap();


}
