package top.oldwei.netty.client.task;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author:weizd
 * @Date:20-4-18
 *
 *
 */
@Slf4j
public class ReadFileTaskV2 implements Runnable {

    private RandomAccessFile rAccessFile;

    private long start;

    private long sliceSize;

    private byte[] readBuffer;

    private int bufferSize = 1024*1024;

    public ReadFileTaskV2(RandomAccessFile rAccessFile,long start, long sliceSize){
        this.start = start;
        this.sliceSize = sliceSize;
        this.readBuffer = new byte[bufferSize];
        this.rAccessFile = rAccessFile;
    }

    public ReadFileTaskV2(String filePath, long start, long sliceSize){
        this.start = start;
        this.sliceSize = sliceSize;
        this.readBuffer = new byte[bufferSize];

        try {
            this.rAccessFile = new RandomAccessFile(filePath,"r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void run() {
        try {

            MappedByteBuffer mapBuffer = rAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,start, this.sliceSize);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for(int offset=0;offset<sliceSize;offset+=bufferSize){
                int readLength;
                if(offset+bufferSize<=sliceSize){
                    readLength = bufferSize;
                }else{
                    readLength = (int) (sliceSize-offset);
                }
                mapBuffer.get(readBuffer, 0, readLength);
                for(int i=0;i<readLength;i++){
                    byte tmp = readBuffer[i];
                    if(tmp=='\n' || tmp=='\r'){
//                        handle(bos.toByteArray());

                        log.info(bos.toString());

                        bos.reset();
                    }else{
                        bos.write(tmp);
                    }
                }
            }
            if(bos.size()>0){
//                handle(bos.toByteArray());
                log.info(bos.toString());
            }
//            cyclicBarrier.await();//测试性能用
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
