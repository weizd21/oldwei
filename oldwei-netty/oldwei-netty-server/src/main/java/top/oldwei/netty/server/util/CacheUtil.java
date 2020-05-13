package top.oldwei.netty.server.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
public class CacheUtil {

    public static Map<String,String> fileCache  =Maps.newConcurrentMap();
}
