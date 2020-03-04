package top.oldwei.redis.config;

import cn.hutool.core.util.StrUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.oldwei.redis.constant.RedisConstant;
import top.oldwei.redis.properties.RedisProperties;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:weizd
 * @Date:20-3-3
 */
@Slf4j
@ConditionalOnProperty(prefix = "oldwei.redis",name = "client-type",havingValue = RedisConstant.CLIENT_REDISSON)
@Configuration
public class RedissonConfig {

    @Resource
    private RedisProperties redisProperties;


    /**
     * 单机模式
     * @return
     */
    @Bean(destroyMethod = "shutdown",name = "redissonClient")
    @ConditionalOnProperty(prefix = "oldwei.redis",name = "mode",havingValue = RedisConstant.REDIS_SINGLE)
    public RedissonClient redissonSingle() {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.setCodec(StringCodec.INSTANCE);
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setPassword(StringUtils.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());
        log.info("注入redisson single client success ");
        return Redisson.create(config);
    }




    /**
     * 哨兵模式
     * @return
     */
    @ConditionalOnProperty(prefix = "oldwei.redis",name = "mode",havingValue = RedisConstant.REDIS_SENTINEL)
    @Bean(destroyMethod = "shutdown",name = "redissonClient")
    public RedissonClient redissonSentinel() {
        Config config = new Config();

        Set<String> nodeSet = new HashSet<>();
        if(StrUtil.isEmpty(redisProperties.getNodes())){
            throw new RuntimeException("RedissonConfig initialize error: oldwei.redis.nodes is null");
        }
        String [] nodeArray = redisProperties.getNodes().split(",");
        if(nodeArray.length == 0){
            throw new RuntimeException("RedissonConfig initialize error: oldwei.redis.nodes is null");
        }
        for(String nodeStr:nodeArray){
            nodeSet.add(nodeStr.startsWith("redis://") ? nodeStr : "redis://"+nodeStr);
        }
        config.setTransportMode(TransportMode.NIO);
        config.setCodec(StringCodec.INSTANCE);
        config.useSentinelServers()
                .setMasterName(redisProperties.getMaster())
                .setDatabase(redisProperties.getDatabase())
                .setPassword(StringUtils.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword())
                .addSentinelAddress(nodeSet.toArray(new String[0]));

        log.info("注入redisson sentinel client success ");
        return Redisson.create(config);
    }

    /**
     * 集群模式
     * @return
     */
    @ConditionalOnProperty(prefix = "oldwei.redis",name = "mode",havingValue = RedisConstant.REDIS_CLUSTER)
    @Bean(destroyMethod = "shutdown",name = "redissonClient")
    public RedissonClient redissonCluster() {
        Config config = new Config();

        if(StrUtil.isEmpty(redisProperties.getNodes())){
            throw new RuntimeException("RedissonConfig initialize error: oldwei.redis.nodes is null");
        }
        String [] nodeArray = redisProperties.getNodes().split(",");
        if(nodeArray.length == 0){
            throw new RuntimeException("RedissonConfig initialize error: oldwei.redis.nodes is null");
        }

        nodeArray = new String[]{"127.0.0.1:5379","127.0.0.1:5380","127.0.0.1:5381","127.0.0.1:5382","127.0.0.1:5383","127.0.0.1:5384"};

        Set<String> nodeSet = new HashSet<>();
        Arrays.stream(nodeArray).forEach(node->{
            nodeSet.add(node.startsWith("redis://") ? node : "redis://"+node);
        });
        config.setTransportMode(TransportMode.NIO);
        config.setCodec(StringCodec.INSTANCE);
        config.useClusterServers()
                .addNodeAddress(nodeSet.toArray(new String[0]))
                .setPassword(StringUtils.isBlank(redisProperties.getPassword()) ? null : redisProperties.getPassword());
        log.info("注入 redisson cluster client success");
        return Redisson.create(config);
    }





}
