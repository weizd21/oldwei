package top.oldwei.demo.thread.pipe;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

@Slf4j
public class Receiver implements Runnable{

    private PipedInputStream pipedInputStream;

    private FileOutputStream fileOutputStream;

    public Receiver(PipedOutputStream pipedOutputStream,String destPath){
        try {
            pipedInputStream = new PipedInputStream(pipedOutputStream);
            fileOutputStream = new FileOutputStream(destPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("未知错误");
        }
    }

    @Override
    public void run() {
        try{
            log.info("start receive >>>>>>>>");
            byte[] buffer = new byte[1024];
            int length;
            while ((length=pipedInputStream.read(buffer)) != -1){
                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>length: {}",length);
                fileOutputStream.write(buffer);
                fileOutputStream.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
