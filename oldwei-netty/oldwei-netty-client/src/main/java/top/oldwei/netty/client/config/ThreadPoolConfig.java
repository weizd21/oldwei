package top.oldwei.netty.client.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import top.oldwei.netty.client.property.ThreadPoolParam;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @Author:weizd
 * @Date:19-12-10
 */
@Configuration
@ConditionalOnBean(ThreadPoolParam.class)
@Slf4j
public class ThreadPoolConfig {



    @Bean
    public ThreadPoolExecutor threadPoolExecutor(@Autowired ThreadPoolParam threadPoolParam){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadPoolParam.getCorePoolSize(),
                threadPoolParam.getMaxPoolSize(),
                threadPoolParam.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(threadPoolParam.getQueueCapacity()),
                new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }


    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(@Autowired ThreadPoolParam threadPoolParam){

        log.info("init threadPoolTaskExecutor begin");

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(threadPoolParam.getCorePoolSize());

        threadPoolTaskExecutor.setMaxPoolSize(threadPoolParam.getMaxPoolSize());

        threadPoolTaskExecutor.setThreadNamePrefix(threadPoolParam.getThreadNamePrefix());

        threadPoolTaskExecutor.setQueueCapacity(threadPoolParam.getQueueCapacity());

        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.initialize();

        log.info("init threadPoolTaskExecutor end");

        return threadPoolTaskExecutor;
    }


}
