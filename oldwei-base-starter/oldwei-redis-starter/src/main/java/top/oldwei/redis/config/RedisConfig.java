package top.oldwei.redis.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import top.oldwei.redis.constant.RedisConstant;
import top.oldwei.redis.handler.RedisService;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-2-28
 */
@Slf4j
@Import(RedisService.class)
@Data
@Component
//@ConfigurationProperties(prefix = "oldwei.redis")
public class RedisConfig {


    private String host = "localhost";

    private String cluster;

    private String master;

    private String nodes;

    private Integer port = 6379;

    // 模式
    private String mode = RedisConstant.REDIS_SINGLE;

    private Integer database = 0;

    private String password;

    private Integer timeOut = 5000;

    private Integer maxActive = 200;

    private Long maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    private Integer maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    private Integer minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;






}
