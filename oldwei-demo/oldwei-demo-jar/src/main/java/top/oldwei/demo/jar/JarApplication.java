package top.oldwei.demo.jar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author:weizd
 * @Date:20-7-31
 */
@Slf4j
@SpringBootApplication
public class JarApplication {

    public static void main(String[] args) {
        log.info("JarApplication main ........");
        SpringApplication.run(JarApplication.class,args);



        new Thread(()->{
            try {
                File file = ResourceUtils.getFile("classpath:script/vidcut.py");

                System.out.println(file.getAbsolutePath());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }).start();
    }
}
