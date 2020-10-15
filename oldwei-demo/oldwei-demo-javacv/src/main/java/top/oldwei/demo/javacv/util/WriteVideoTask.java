package top.oldwei.demo.javacv.util;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/10/14 上午9:12
 */
@Slf4j
public class WriteVideoTask implements Runnable{

    private File file;

    private FFmpegFrameRecorder recorder;

    private long startTimestamp;

    private long peerTime;

    public WriteVideoTask(){

    }
    public WriteVideoTask(File file,FFmpegFrameRecorder recorder,long startTimestamp,long peerTime){
        this.file = file;
        this.recorder = recorder;
        this.startTimestamp = startTimestamp;
        this.peerTime = peerTime;
    }
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try{
            log.info("[{}] 段开始 ",startTimestamp);
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
            Frame frame = null;
            grabber.start();
            recorder.start();
            while (true){
                frame = grabber.grab();
                if(frame == null){
                    break;
                }
                if(frame.timestamp > peerTime*1000000+startTimestamp){
                    break;
                }
                if(frame.timestamp > startTimestamp){
                    recorder.record(frame);
                }
            }
            grabber.close();
            recorder.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("[{}] 段结束,耗时： 【{} ms】",startTimestamp,System.currentTimeMillis() - start);
    }
}
