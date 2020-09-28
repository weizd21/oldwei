package top.oldwei.demo.spark;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/9/28 上午11:04
 */
@Slf4j
public class VideoTest {

    private static String path = "/home/weizd/label/vott/source/Video_0927_1.mp4";

    private static String picFile = "/home/weizd/label/vott/source/firstFrame1.jpg";

    public static void main(String[] args) throws Exception{
        File file = new File(path);
        // splitVideo(file,10);
        // splitVideoByTime(file,10);
        selectFrame2Pic(path,1,new File(picFile));
        log.info("{} 时长： {}",path,getVideoDuration(path));
    }


    public static long getVideoDuration(String videoPath) throws Exception {
        long duration = 0L;
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(videoPath);
        grabber.start();
        duration = grabber.getLengthInTime() /(1000*1000);
        grabber.close();
        return duration;
    }

    public static void selectFrame2Pic(String videoPath,int frameIndex,File picFile) throws Exception{
        ImageIO.write(frameToBufferedImage(getFrameByIndex(videoPath,frameIndex)),"jpg",picFile);
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
        int frame_number = grabber.getLengthInFrames();
        recorder = getFFmpegFrameRecorder(path +"_splitByTime_" +"."+FileUtil.extName(path),grabber);
        recorder.start();
        for(int i=0;i<frame_number;i++){
            frame = grabber.grabFrame();
            recorder.record(frame);
        }
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
        log.info("fFmpegFrameGrabber.getVideoFrameRate() {}:",fFmpegFrameGrabber.getVideoFrameRate());
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
                frameNum = 1;
                recorder.close();
                recorder = getFFmpegFrameRecorder(path +"_"+ String.valueOf(index) +"."+FileUtil.extName(path),fFmpegFrameGrabber );
                recorder.start();
            }
            num++;
            frame = fFmpegFrameGrabber.grabFrame();
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


//    /**
//     * 视频文件指定时间段的帧截取
//     * @param file
//     * @param start
//     * @param end
//     */
//    public static List<File> videoIntercept(File file, Integer start, Integer end) {
//        Frame frame = null;
//        List<File> files = new ArrayList<>();
//        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(file);
//        String filePath = "D://video//images//";
//        String fileTargetName = "movie";
//        try {
//            fFmpegFrameGrabber.start();
//            int ftp = fFmpegFrameGrabber.getLengthInFrames();
//            System.out.println("开始视频提取帧");
//            for (int i=0 ; i < ftp ; i++){
//                if( i >= start && i <= end){
//                    frame = fFmpegFrameGrabber.grabImage();
//                    doExecuteFrame(frame, filePath, fileTargetName, i ,files);
//                }
//            }
//            System.out.println("============运行结束============");
//            fFmpegFrameGrabber.stop();
//        } catch (IOException E) {
////      Loggers.ERROR.error("视频抽帧异常", e);
//        }
//        return files;
//    }
//
//    public static void doExecuteFrame(Frame frame, String targetFilePath, String targetFileName, int index ,List<File> files) {
//        if ( frame == null || frame.image == null) {
//            return;
//        }
//        Java2DFrameConverter converter = new Java2DFrameConverter();
//        String imageMat = "jpg";
//        String fileName = targetFilePath + targetFileName + "_" + index + "." + imageMat;
//        BufferedImage bi = converter.getBufferedImage(frame);
//        File output = new File(fileName);
//        files.add(output);
//        try{
//            ImageIO.write(bi, imageMat, output);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        List<File> files = videoIntercept(new File("D://video//1553583033205-480p.mp4"), 10, 20);
//        System.out.println(files);
//    }






//    public static void main(String[] args) {
//
//        boolean isStart = true;// 该变量建议设置为全局控制变量，用于控制录制结束
//        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(url);
//        // 微秒 大概为设置时间的两倍 TimeoutOption代码在示例文章二
//        grabber.setOption(TimeoutOption.RW_TIMEOUT.getKey(), timeout);
//        // rtsp 默认udp 丢包 改为tcp
//        grabber.setOption("rtsp_transport", "tcp");
//        grabber.start();
//        //好多视频熟悉可以获取后打印 示例几个
//        log.info("ImageWidth:" + grabber.getImageWidth());
//        log.info("ImageHeight:" + grabber.getImageHeight());
//        log.info("AudioChannels:" + grabber.getAudioChannels());
//        log.info("Format:" + grabber.getFormat());
//        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, imageWidth, imageHeight, grabber.getAudioChannels());
//        recorder.setFrameRate(grabber.getFrameRate());
////					recorder.setAudioBitrate(grabber.getAudioBitrate());
////					recorder.setSampleRate(grabber.getSampleRate());
////					recorder.setGopSize(2);
//        recorder.setFormat(Format);
//        recorder.setAudioCodecName("aac");
//        recorder.setVideoCodec(grabber.getVideoCodec());
//        Frame f = null;
//        //如果想截取规定时间段视频 请看系列文章二
//        while (isStart) {
//            f = grabber.grabFrame();
//            recorder.record(f);
//        }
//        recorder.stop();
//        recorder.release();
//        grabber.stop();
//        grabber.release();
//
//
//
//    }





}
