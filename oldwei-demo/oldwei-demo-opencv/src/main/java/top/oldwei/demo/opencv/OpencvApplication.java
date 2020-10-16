package top.oldwei.demo.opencv;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/10/16 上午11:29
 */
@Slf4j
public class OpencvApplication {



    public static void main(String[] args) throws Exception{
        log.info("java.library.path: {}",System.getProperty("java.library.path"));

        addLibraryDir("/soft/opencv/release/share/OpenCV/java");

        log.info("java.library.path: {}",System.getProperty("java.library.path"));

        String file1 = "/home/weizd/video/sm.mp4";

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        System.out.println(Core.VERSION);
        long start = System.currentTimeMillis();
        VideoCapture videoCapture = new VideoCapture(file1);
        int height = (int)videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        int width = (int)videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        double fps = videoCapture.get(Videoio.CAP_PROP_FPS);
        VideoWriter videoWriter = new VideoWriter("/home/weizd/video/sm_opencv.mp4",VideoWriter.fourcc('D', 'I', 'V', 'X'),fps,new Size(width,height));
        System.out.println(videoCapture.isOpened());
        Mat frame = new Mat();
//        if(videoCapture.isOpened()) {
//            while (true) {
//                boolean res = videoCapture.read(frame);
//                if (!res || null == frame) {
//                    break;
//                }
//                videoWriter.write(frame);
//            }
//        }
        log.info("opencv take time :【{} ms】",System.currentTimeMillis() - start);
    }


    /**
     * 动态设置 java.library.path 来扩展动态库
     *
     * 利用反射机制
     * @param libraryPath
     * @throws IOException
     */
    public static void addLibraryDir(String libraryPath) throws IOException {
        try {
            Field field = ClassLoader.class.getDeclaredField("usr_paths");
            field.setAccessible(true);
            String[] paths = (String[]) field.get(null);
            for (int i = 0; i < paths.length; i++) {
                if (libraryPath.equals(paths[i])) {
                    return;
                }
            }
            String[] tmp = new String[paths.length + 1];
            System.arraycopy(paths, 0, tmp, 0, paths.length);
            tmp[paths.length] = libraryPath;
            field.set(null, tmp);
        } catch (IllegalAccessException e) {
            throw new IOException(
                    "Failedto get permissions to set library path");
        } catch (NoSuchFieldException e) {
            throw new IOException(
                    "Failedto get field handle to set library path");
        }
    }


}
