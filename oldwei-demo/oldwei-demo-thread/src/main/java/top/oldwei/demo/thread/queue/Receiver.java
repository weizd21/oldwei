package top.oldwei.demo.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Receiver implements Runnable{

    private BlockingQueue<Item> queue;

    public Receiver(BlockingQueue<Item> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        Item item = null;
        while (true){
            try {
                log.info("begin take ");
                item = queue.take();
                log.info("end take ");
                log.info("receiver data : {}",new String(item.getBytes()));
                if(item.getNum() == 99 || item == null){
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
