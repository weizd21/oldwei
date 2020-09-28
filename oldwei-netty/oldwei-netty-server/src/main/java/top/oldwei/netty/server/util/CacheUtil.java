package top.oldwei.netty.server.util;


import com.google.common.collect.Maps;
import top.oldwei.netty.server.domain.TransferFileInfo;

import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-5-13
 */
public class CacheUtil {

    public static Map<String,TransferFileInfo> fileInfoCache = Maps.newConcurrentMap();

}
