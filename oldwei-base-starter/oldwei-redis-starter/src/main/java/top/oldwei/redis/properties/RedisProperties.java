package top.oldwei.redis.properties;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import top.oldwei.redis.config.JedisConfig;
import top.oldwei.redis.config.RedissonConfig;
import top.oldwei.redis.handler.JedisService;

/**
 * redis配置信息
 *
 * @author weizd
 */
@Data
@Component
@Import({JedisConfig.class, RedissonConfig.class,JedisService.class})
@ConfigurationProperties(prefix = "oldwei.redis")
public class RedisProperties {

    private String host = "localhost";

    private String cluster;

    private String master;

    private String nodes;

    private Integer port = 6379;

    private Integer database = 0;

    private String password;

    private Integer timeOut = 5000;

    private Integer maxActive = 200;

    private Long maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    private Integer maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    private Integer minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;


}
