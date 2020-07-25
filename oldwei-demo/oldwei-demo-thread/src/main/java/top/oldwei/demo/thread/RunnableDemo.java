package top.oldwei.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author:weizd
 * @Date:20-7-22
 */
@Slf4j
public class RunnableDemo implements Runnable {


    @Override
    public void run() {
        log.info("runnableDemo enter ");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("runnableDemo leave ");
    }

}
