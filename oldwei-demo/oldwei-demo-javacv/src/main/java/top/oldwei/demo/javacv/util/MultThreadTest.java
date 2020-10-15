package top.oldwei.demo.javacv.util;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static top.oldwei.demo.javacv.util.VideoUtil.*;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/10/14 上午9:38
 */
@Slf4j
public class MultThreadTest {



    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        String path = "/home/weizd/ve/sm.mp4";
//        path = "/home/weizd/ve/Video_0927_1.mp4";
        File file = new File(path);
        long peerTime = 30*60;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
        grabber.start();
        long totalTime = grabber.getLengthInTime()/(1000*1000);
        long videoNum = totalTime%peerTime == 0 ? totalTime/peerTime : totalTime/peerTime +1;

        for(int i = 0;i<videoNum;i++){
            FFmpegFrameRecorder recorder = getFFmpegFrameRecorder(path +"_"+ i +"_" +"."+ FileUtil.extName(path),grabber);
            WriteVideoTask writeVideoTask = new WriteVideoTask(file,recorder,i*peerTime*1000000,peerTime);
            executorService.execute(writeVideoTask);
            log.info("videoNum : [{}]",i);
        }


//        Frame frame = null;
//        int videoNum = 0;
//        while (true){
//            frame = grabber.grab();
//            if(frame == null){
//                break;
//            }
//            if(frame.timestamp/1000000 == peerTime*videoNum){
//                log.info("videoNum : [{}]",videoNum);
////                FFmpegFrameRecorder recorder = getFFmpegFrameRecorder(path +"_"+ videoNum +"_" +"."+ FileUtil.extName(path),grabber);
//                videoNum++;
//            }else {
//
//            }
//        }
//        log.info("[{}] timestamp is : [{} ms]",path,grabber.getTimestamp());

        grabber.stop();

        log.info("take time is [{} ms]",System.currentTimeMillis() - start);
    }






}
