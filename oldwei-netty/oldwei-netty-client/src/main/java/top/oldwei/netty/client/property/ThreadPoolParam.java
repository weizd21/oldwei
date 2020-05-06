package top.oldwei.netty.client.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:weizd
 * @Date:19-12-10
 */
@Data
@Configuration
@EnableConfigurationProperties(ThreadPoolParam.class)
@ConfigurationProperties(prefix = "oldwei.executor")
public class ThreadPoolParam {

    private String name;

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private String threadNamePrefix;

    private Long keepAliveTime;

}
