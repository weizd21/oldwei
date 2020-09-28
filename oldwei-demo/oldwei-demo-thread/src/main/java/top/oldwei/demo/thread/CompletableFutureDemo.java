package top.oldwei.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weizd
 * @Date:20-7-23
 */
@Slf4j
public class CompletableFutureDemo {


    public static void main(String[] args) throws Exception{


        CompletableFuture<String> completableFuture = new CompletableFuture();

        // 手动结束
        completableFuture.complete("this is return value ");

        String result = completableFuture.get();

        log.info("result: {}",result);


        log.error("----------------------------");
        log.error("----------------------------");

        // 无返回值的
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            try{
                log.info("runAsync thread is running ");
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){

            }
            log.info("runAsync thread run over");
        });
        completableFuture1.get();


        log.error("----------------------------");
        log.error("----------------------------");

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {

            try {
                log.info("supplyAsync thread is running ");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "supplyAsync thread return value";
        });

        String result2 = completableFuture2.get();
        log.info("result2 : {}",result2);

        log.error("----------------------------");
        log.error("----------------------------");


        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(" 1 ");
            return " 1 ";
        }).thenApply(first -> {

            log.info(" 2 ");
            return first+  " 2 ";
        }).thenApply(second -> {

            log.info(" 3 ");
            return second+ "3";
        });

        log.info(" 4");
        String result3 = completableFuture3.get();
        log.info("result3 : {}",result3);


        log.error("----------------------------");
        log.error("----------------------------");


    }





}
