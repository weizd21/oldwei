package top.oldwei.demo.thread;

import cn.hutool.core.date.SystemClock;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weizd
 * @Date:20-7-22
 */
@Slf4j
public class ThreadPool {

    private static long minute_2 = 2*60;

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 放入队列中,依次执行
        RunnableDemo runnableDemo = null;
        for(int i=0;i< 5;i++){
            runnableDemo = new RunnableDemo();
            executorService.submit(runnableDemo);
        }





        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        long startTime = SystemClock.now();
        while (!future.isDone()){
            long takeTime = SystemClock.now() - startTime;
            log.info("takeTime : {}",takeTime);
            if(takeTime > minute_2 ){
                // 超时处理
                future.cancel(true);
                log.info("线程超时,取消线程 [{}]",takeTime);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        if(future.isCancelled()){
            // 线程被取消
            log.info("线程被取消");
        }else {
            log.info("线程返回结果:{}",future.get());
        }
        executorService.shutdown();
    }

}
