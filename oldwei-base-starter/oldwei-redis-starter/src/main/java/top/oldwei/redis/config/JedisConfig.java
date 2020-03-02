package top.oldwei.redis.config;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import top.oldwei.redis.constant.RedisConstant;
import top.oldwei.redis.properties.RedisProperties;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-2
 */
@Slf4j
@Configuration
public class JedisConfig {

    /**
     * 映射配置文件
     */
    @Resource
    private RedisProperties redisProperties;

    /**
     * 连接池基础信息
     * @return
     */
    @Bean(value = "jedisPoolConfig")
    public JedisPoolConfig initJedisConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(false);
        jedisPoolConfig.setTestWhileIdle(true);
        log.info("初始化jedisConfig连接池对象成功");
        return jedisPoolConfig;
    }

    /**
     * 单机连接池
     * @param jedisPoolConfig
     * @return
     */
    @ConditionalOnProperty(prefix = "oldwei.redis",name = "mode",havingValue = RedisConstant.REDIS_SINGLE)
    @Bean(value = "redisPool")
    public JedisPool jedisPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
        log.info("---注入jedisPool成功---");
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,redisProperties.getHost(),redisProperties.getPort(),redisProperties.getTimeOut(),redisProperties.getPassword());
        return jedisPool;
    }


    /**
     * 哨兵连接池
     * @param jedisPoolConfig
     * @return
     */
    @ConditionalOnProperty(prefix = "oldwei.redis",name = "mode",havingValue = RedisConstant.REDIS_SENTINEL)
    @Bean(value = "redisPool")
    public JedisSentinelPool jedisSentinelPool(@Qualifier(value = "jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
        log.info("---注入jedisSentinelPool成功---");
        Set<String> nodeSet = new HashSet<>();
        if(StrUtil.isEmpty(redisProperties.getNodes())){
            throw new RuntimeException("redisConfig initialize error: oldwei.redis.nodes is null");
        }
        String [] nodeArray = redisProperties.getNodes().split(",");
        if(nodeArray.length == 0){
            throw new RuntimeException("redisConfig initialize error: oldwei.redis.nodes is null");
        }
        for(String nodeStr:nodeArray){
            nodeSet.add(nodeStr);
        }
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(redisProperties.getMaster(),nodeSet,jedisPoolConfig,redisProperties.getTimeOut(),redisProperties.getPassword());
        return jedisSentinelPool;
    }






}
