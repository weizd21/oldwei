package top.oldwei.websocket.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-3-20
 */
@Component
@ConfigurationProperties(prefix = "oldwei")
@Data
public class Properties {

    private List<String> lists;


    private Map<String,String> hosts;


}
