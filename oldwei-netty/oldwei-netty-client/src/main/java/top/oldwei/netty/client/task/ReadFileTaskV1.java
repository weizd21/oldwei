package top.oldwei.netty.client.task;

import java.util.concurrent.Callable;

/**
 * @Author:weizd
 * @Date:20-4-18
 */
public class ReadFileTaskV1 implements Callable {

    private long start;

    private long sliceSize;

    private byte[] readBuffer;

    private int bufferSize = 1024*1024;

    public ReadFileTaskV1(long start,long sliceSize){
        this.start = start;
        this.sliceSize = sliceSize;
        this.readBuffer = new byte[bufferSize];
    }




    @Override
    public Object call() throws Exception {
        return null;
    }
}
