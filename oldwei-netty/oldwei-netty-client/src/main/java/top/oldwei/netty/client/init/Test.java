package top.oldwei.netty.client.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author:weizd
 * @Date:20-4-20
 */
@Slf4j
@Component
public class Test implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        File  file = new File("/home/weizd/dataset/dataset/RossmanSales.csv");

        long fileLength = file.length();

        long everySize = fileLength/4;


        long start = 0;

        for(;start > fileLength-1;){



        }



    }
}
