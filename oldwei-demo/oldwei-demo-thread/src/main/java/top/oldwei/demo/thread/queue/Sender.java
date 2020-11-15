package top.oldwei.demo.thread.queue;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: weizd
 * @time: 2020/11/14 5:30 下午
 */
@Slf4j
public class Sender implements Runnable{

    private File file;

    private BlockingQueue<Item> queue;

    public Sender(File file, BlockingQueue<Item> queue){
        this.file = file;
        this.queue = queue;
    }

    @Override
    public void run() {

        Item item = null;
        for(int i=0;i<100;i++){
            item = new Item();
            item.setBytes(("this index is : "+i).getBytes());
            item.setNum(i);
            log.info("add item to queue data: {} , num: {}",new String(item.getBytes()),item.getNum());
            try {
                queue.put(item);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("<<<<<< add item to queue success >>>>>>");
            try {
                // Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
