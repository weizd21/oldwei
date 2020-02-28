package top.oldwei.redis.config;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import top.oldwei.redis.annotation.Describe;
import top.oldwei.redis.constant.RedisConstant;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-2-28
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "oldwei.redis")
public class RedisConfig {


    @Describe(value = "redis 单点地址")
    private String host = "localhost";

    @Describe(value = "redis 集群地址")
    private String cluster;

    @Describe(value = "redis 哨兵主")
    private String master;

    @Describe(value = "redis 哨兵从节点")
    private String nodes;

    @Describe(value = "redis 端口")
    private Integer port = 6379;

    @Describe(value = "redis 数据库")
    private Integer database = 0;

    @Describe(value = "redis 密码")
    private String password;

    @Describe(value = "redis 读取超时时间")
    private Integer timeOut = 5000;

    @Describe(value = "redis 最大连接数")
    private Integer maxActive = 200;

    @Describe(value = "redis 获取连接时的最大等待毫秒数")
    private Long maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    @Describe(value = "redis 最大空闲连接数")
    private Integer maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    @Describe(value = "redis 最小空闲连接数")
    private Integer minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;


    @Bean(value = "jedisPoolConfig")
    public JedisPoolConfig initJedisConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(false);
        jedisPoolConfig.setTestWhileIdle(true);
        log.info("初始化jedisConfig连接池对象成功");
        return jedisPoolConfig;
    }


    @ConditionalOnProperty(prefix = "oldwei.redis",name = "type",havingValue = RedisConstant.REDIS_SINGLE)
    @Bean(value = "redisPool")
    public JedisPool jedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
        log.info("---注入jedisPool成功---");
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeOut.intValue(),password);
        return jedisPool;
    }

    @ConditionalOnProperty(prefix = "oldwei.redis",name = "type",havingValue = RedisConstant.REDIS_SENTINEL)
    @Bean(value = "redisPool")
    public JedisSentinelPool jedisSentinelPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
        log.info("---注入jedisSentinelPool成功---");
        Set<String> nodeSet = new HashSet<>();
        if(StrUtil.isEmpty(nodes)){
            throw new RuntimeException("config initialize error: oldwei.redis.nodes is null");
        }
        String [] nodeArray = nodes.split(",");
        if(nodeArray.length == 0){
            throw new RuntimeException("config initialize error: oldwei.redis.nodes is null");
        }
        for(String nodeStr:nodeArray){
            nodeSet.add(nodeStr);
        }
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(master,nodeSet,jedisPoolConfig,timeOut,password);
        return jedisSentinelPool;
    }

}
