package top.oldwei.netty.client.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import top.oldwei.netty.client.task.ReadFileTaskV2;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:weizd
 * @Date:20-4-20
 */
@Slf4j
@Component
public class Test implements CommandLineRunner {


    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Resource
    private ThreadPoolExecutor threadPoolExecutor;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("aa\nbb");
        System.out.println("aa\rbb");
        System.out.println("aa\n\rbb");

        String filePath = "/home/weizd/dataset/dataset/RossmanSales.csv";

//        filePath = "/home/weizd/dataset/dataset/bike_nohead.csv";
//
//        filePath = "/home/weizd/dataset/dataset/balance_scale_nohead.csv";

        filePath = "/home/weizd/dataset/dataset/ulike_nohead.csv";

        File  file = new File(filePath);

        RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");

        int threadNum = 4;

        long fileLength = file.length();

        log.info("fileLength: 【{}】",fileLength);

        long everySize = fileLength/threadNum;

        long finalSize = everySize;

        long start = 0;

        boolean end = true;

        long startTime = System.currentTimeMillis();


        for(;end;){

            log.info("------> "+start);


            if(start + everySize >= fileLength-1){
                finalSize = fileLength-1 - start;
                end = false;
            }else {
                //
                randomAccessFile.seek(start+finalSize);
                byte tmp = randomAccessFile.readByte();
                while (tmp != '\n' && tmp != '\r'){
                    finalSize++;
                    if(start + finalSize > fileLength-1){
                        break;
                    }
                    randomAccessFile.seek(start+finalSize);
                    tmp = randomAccessFile.readByte();
                }
            }

//            Runnable runnable = new ReadFileTaskV2(randomAccessFile,start,finalSize);

            Runnable runnable = new ReadFileTaskV2(filePath,start,finalSize);

//            threadPoolTaskExecutor.execute(runnable);

            threadPoolExecutor.execute(runnable);

            log.info("endIndex ----> 【{}】",start+finalSize);

            start = start + finalSize;
            finalSize = everySize;
        }

        threadPoolExecutor.shutdown();

        while(!threadPoolExecutor.isTerminated());

        log.info("take 【{}】 ms",System.currentTimeMillis() - startTime);

        log.info(threadPoolTaskExecutor.getThreadNamePrefix());









    }
}
