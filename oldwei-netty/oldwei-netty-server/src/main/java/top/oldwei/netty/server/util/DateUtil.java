package top.oldwei.netty.server.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @Author:weizd
 * @Date:20-5-15
 */
public class DateUtil {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    /**
     * 获取格式化日期时间 字符串
     * @return
     */
    public static String getDateTimeStr(){
        return LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
    }
}
