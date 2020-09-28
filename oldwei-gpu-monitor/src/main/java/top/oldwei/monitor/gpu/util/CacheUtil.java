package top.oldwei.monitor.gpu.util;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-8-3
 */
public class CacheUtil {


    public static Map<String, Channel> sessionMap = Maps.newConcurrentMap();


}
