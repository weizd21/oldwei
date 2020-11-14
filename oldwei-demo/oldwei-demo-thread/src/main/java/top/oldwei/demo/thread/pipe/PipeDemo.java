package top.oldwei.demo.thread.pipe;

import java.io.File;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PipeDemo {


    public static void main(String[] args) throws Exception{

//        PipedInputStream pipedInputStream = new PipedInputStream();
//        PipedOutputStream pipedOutputStream = new PipedOutputStream();
//        pipedOutputStream.connect(pipedInputStream);

        String filePath = "/Users/weizd/test/http-client-2.2.3.jar";

        String destPath = "/Users/weizd/test/dest/http.jar";

        File file = new File(filePath);

        Sender sender = new Sender(file);

        Receiver receiver = new Receiver(sender.getPipedOutputStream(),destPath);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(sender);
        executorService.execute(receiver);












    }
}
