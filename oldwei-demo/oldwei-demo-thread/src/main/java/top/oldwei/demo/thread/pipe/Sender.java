package top.oldwei.demo.thread.pipe;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class Sender implements Runnable {

    private PipedOutputStream pipedOutputStream = new PipedOutputStream();

    public PipedOutputStream getPipedOutputStream(){
        return pipedOutputStream;
    }

    private File file;

    public Sender(File file){
        this.file = file;
    }

    @Override
    public void run() {
        try {
            log.info("start sender 1-----------> ");
//            Thread.sleep(10000);
            log.info("start sender 2-----------> ");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
            long sliceSize = file.length();
            MappedByteBuffer mapBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0, sliceSize);
            int bufferSize = 1024;
            byte[] readBuffer = new byte[bufferSize];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            for(int offset=0;offset<sliceSize;offset+=bufferSize){
                int readLength;
                if(offset+bufferSize<=sliceSize){
                    readLength = bufferSize;
                }else{
                    readLength = (int) (sliceSize-offset);
                }
                mapBuffer.get(readBuffer, 0, readLength);
                pipedOutputStream.write(readBuffer);
                pipedOutputStream.flush();
                log.info(">>>>>>>-------------> {}",offset);
            }
            log.info("---------------> 1 send thread end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pipedOutputStream != null){
                try {
                    pipedOutputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            log.info("---------------> 2 send thread end");
        }


    }
}
