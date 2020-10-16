package top.oldwei.demo.jar;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
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
                log.info("java.library.path: {}",System.getProperty("java.library.path"));

                String file1 = "/home/weizd/video/sm.mp4";

                // System.load("/soft/opencv/opencv-3.4.3/build/lib/libopencv_java343.so");

                //File file = ResourceUtils.getFile("classpath:script/libopencv_java343.so");

                // System.load("classpath:script/libopencv_java343.so");

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

                System.out.println(Core.VERSION);

                VideoCapture videoCapture = new VideoCapture(file1);

                System.out.println(videoCapture.isOpened());


            } catch (Exception e) {
                e.printStackTrace();
            }


        }).start();
    }
}
