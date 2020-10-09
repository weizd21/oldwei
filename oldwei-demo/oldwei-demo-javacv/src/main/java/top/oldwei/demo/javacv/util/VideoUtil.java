package top.oldwei.demo.javacv.util;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/30 上午10:47
 */

@Slf4j
public class VideoUtil {


    private static String path = "/home/weizd/video/Video_0927_1.mp4";

    private static String picFile = "/home/weizd/video/firstFrame1.jpg";
    private static String picFileTimestamp = "/home/weizd/video/firstFrame_timestamp.jpg";

    public static void main(String[] args) throws Exception{
        File file = new File(path);
        testVideoProp(path);
        splitVideoByTime(file,10);
//         splitVideo(file,20);
        // splitVideoByTime(file,10);
//        selectIndexFrame2Pic(path,1,new File(picFile));
//        selectTimestampFrame2Pic(path,50000000L,new File(picFileTimestamp));
        log.info("{} 时长： {}",path,getVideoDuration(path));
    }




    public static void testVideoProp(String videoFile) throws Exception{
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoFile);
        grabber.start();
        log.info("getImageHeight() {}:",grabber.getImageHeight());
        log.info("getImageWidth() {}:",grabber.getImageWidth());

        log.info("getLengthInVideoFrames() {}:",grabber.getLengthInVideoFrames());
        log.info("getLengthInFrames() {}:",grabber.getLengthInFrames());

        log.info("getVideoFrameRate() {}:",grabber.getVideoFrameRate());
        log.info("getFrameRate() {}:",grabber.getFrameRate());

        log.info("getAudioChannels() {}:",grabber.getAudioChannels());


        grabber.close();
    }


    /**
     * 获取视频时长 秒
     * @param videoPath
     * @return
     * @throws Exception
     */
    public static long getVideoDuration(String videoPath) throws Exception {
        long duration = 0L;
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoPath);
        grabber.start();
        duration = grabber.getLengthInTime() /(1000*1000);
        grabber.close();
        return duration;
    }

    public static void selectIndexFrame2Pic(String videoPath,int frameIndex,File picFile) throws Exception{
        frame2Pic(getFrameByIndex(videoPath,frameIndex),"jpg",picFile);
    }

    public static void selectTimestampFrame2Pic(String videoPath,long timestamp,File picFile) throws Exception{
        frame2Pic(getFrameByTimestamp(videoPath,timestamp),"jpg",picFile);
    }

    public static void frame2Pic(Frame frame, String formatName, File picFile) throws Exception{
        ImageIO.write(frameToBufferedImage(frame),formatName,picFile);
    }


    public static Frame getFrameByTimestamp(String videoPath,long timestamp)throws Exception{
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoPath);
        grabber.start();
        grabber.setAudioTimestamp(timestamp);
        Frame frame = grabber.grabImage();
        grabber.close();
        return frame;
    }


    public static Frame getFrameByIndex(String videoPath,int index) throws Exception{
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoPath);
        grabber.start();
        grabber.setFrameNumber(index);
        Frame frame = grabber.grabImage();
        grabber.close();
        return frame;
    }

    public static BufferedImage frameToBufferedImage(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }


    public static void splitVideoByTime(File file,long peerTime)throws Exception{
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file);
        grabber.start();
        FFmpegFrameRecorder recorder = null;
        Frame frame = null;
        int videoNum = 1;
        recorder = getFFmpegFrameRecorder(path +"_"+ videoNum +"_" +"."+ FileUtil.extName(path),grabber);
        recorder.start();
        while (true){
            frame = grabber.grab();
            if(frame == null){
                break;
            }
            if(frame.timestamp/1000000 < peerTime*videoNum){
                recorder.record(frame);
            }else {
                videoNum++;
                recorder.close();
                recorder = getFFmpegFrameRecorder(path +"_"+ videoNum +"_" +"."+ FileUtil.extName(path),grabber);
                recorder.start();
            }
        }
        log.info("-----> {} <------",recorder.getTimestamp());
//        log.info("-----> {} <------",frame.timestamp);
        recorder.close();
        grabber.close();
    }


    public static void splitVideo(File file,int peerLength) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = FFmpegFrameGrabber.createDefault(file);
        fFmpegFrameGrabber.start();
        int imageWidth = fFmpegFrameGrabber.getImageWidth();
        int imageHeight = fFmpegFrameGrabber.getImageHeight();

        log.info("fFmpegFrameGrabber.getImageHeight() {}:",fFmpegFrameGrabber.getImageHeight());
        log.info("fFmpegFrameGrabber.getImageWidth() {}:",fFmpegFrameGrabber.getImageWidth());
        log.info("fFmpegFrameGrabber.getLengthInVideoFrames() {}:",fFmpegFrameGrabber.getLengthInVideoFrames());
        log.info("fFmpegFrameGrabber.getLengthInFrames() {}:",fFmpegFrameGrabber.getLengthInFrames());
        log.info("fFmpegFrameGrabber.getVideoFrameRate() {}:",fFmpegFrameGrabber.getVideoFrameRate());
        log.info("fFmpegFrameGrabber.getFrameRate() {}:",fFmpegFrameGrabber.getFrameRate());
        log.info("fFmpegFrameGrabber.getAudioChannels() {}:",fFmpegFrameGrabber.getAudioChannels());


        FFmpegFrameRecorder recorder = null;
        Frame frame = null;
        frame = fFmpegFrameGrabber.grabFrame();
        double oneVideoFrameNum = peerLength * fFmpegFrameGrabber.getVideoFrameRate();
        long index = 0 ;
        long frameNum = 1;
        recorder = getFFmpegFrameRecorder(path +"_"+ String.valueOf(index) +"."+FileUtil.extName(path),fFmpegFrameGrabber );
        recorder.start();
        long num = 0;
        while (frame != null){
            if(frameNum < oneVideoFrameNum){
                recorder.record(frame);
                frameNum++;
            }else{
                index++;
                log.info("frameNum: {}",frameNum);
                frameNum = 1;
                recorder.close();
                recorder = getFFmpegFrameRecorder(path +"_"+ String.valueOf(index) +"."+FileUtil.extName(path),fFmpegFrameGrabber );
                recorder.start();
            }
            num++;
            frame = fFmpegFrameGrabber.grab();
        }
        log.info("num: {}",num);
        recorder.stop();
        fFmpegFrameGrabber.stop();;
    }

    public static FFmpegFrameRecorder getFFmpegFrameRecorder(String outFilePath,FFmpegFrameGrabber grabber){
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(new File(outFilePath), grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        // 视频相关设置
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.setVideoCodec(grabber.getVideoCodec());
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        // 音频相关配置
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setAudioCodec(grabber.getAudioCodec());
        return recorder;
    }







}
