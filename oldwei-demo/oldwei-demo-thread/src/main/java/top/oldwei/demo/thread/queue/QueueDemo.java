package top.oldwei.demo.thread.queue;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @description:
 * @author: weizd
 * @time: 2020/11/14 5:31 下午
 */
public class QueueDemo {

    private static int initQueueSize = 10;

    public static void main(String[] args) {
//        BlockingQueue<Item> blockingQueue = new ArrayBlockingQueue<>(initQueueSize);
//
//        Sender sender = new Sender(new File(""),blockingQueue);
//
//        Receiver receiver = new Receiver(blockingQueue);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        executorService.execute(sender);
//        executorService.execute(receiver);
//
//        executorService.shutdown();


        System.out.println(UUID.randomUUID().toString().replace("-",""));









    }



}
