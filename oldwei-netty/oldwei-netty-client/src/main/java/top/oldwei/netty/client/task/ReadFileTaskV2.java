package top.oldwei.netty.client.task;

/**
 * @Author:weizd
 * @Date:20-4-18
 *
 *
 */
public class ReadFileTaskV2 implements Runnable {

    private long start;

    private long sliceSize;

    private byte[] readBuffer;

    private int bufferSize = 1024*1024;

    public ReadFileTaskV2(long start,long sliceSize){
        this.start = start;
        this.sliceSize = sliceSize;
        this.readBuffer = new byte[bufferSize];
    }





    @Override
    public void run() {

    }
}
