package top.oldwei.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.concurrent.Callable;

/**
 * @Author:weizd
 * @Date:20-7-22
 */
@Slf4j
public class CallableDemo implements Callable<String> {
    @Override
    public String call() throws Exception {


        log.info("call function is doing");
        Thread.sleep(10000);

        return LocalDate.now().toString();
    }

}
