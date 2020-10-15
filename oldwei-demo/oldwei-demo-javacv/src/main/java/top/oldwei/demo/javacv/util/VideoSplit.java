package top.oldwei.demo.javacv.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @author weizd
 * @version 1.0
 * @date 2020/10/13 下午6:22
 */
@Slf4j
public class VideoSplit {
    public static void main(String[] args) {

        @SuppressWarnings("resource")
//        Scanner scanner = new Scanner(System.in);
        String file1 = "/home/weizd/video/sm.mp4";
        String file2 = "/home/weizd/video/sm.mp4";
        int num = 20;
        long start = System.currentTimeMillis();
        getFileSplit(file1, num);
        log.info("take time is : 【{} s】",(System.currentTimeMillis() - start)/1000);

//        System.out.println("1.切割视频     2.合成视频");
//        String choice = scanner.nextLine();
//        switch (choice) {
//            case "1":
//                int num = 20;
//                getFileSplit(file1, num);
//                break;
//
//            case "2":
//                int tempcount  = 5;
//                merge(file1, file2, tempcount);
//            default:
//                break;
//        }
    }

    /**
     * @author shirui
     * @param file
     * @param num
     * 切割视频
     */

    public static void getFileSplit(String file, int num) {

        try {
            RandomAccessFile raf = new RandomAccessFile(new File(file), "r"); //读取文件

            long length = raf.length();  // 获取文件的大小
            long maxSize = length / num;  // 每一份的大小
            long offset = 0L;            // 偏移量
            for (int i = 0; i < num - 1; i++) {			// num-1 是因为最后一份不知道大小
//                long fbegin = offset;					// 文件开始的位置
                long fbegin = maxSize*i;					// 文件开始的位置
                long fend = (i+1) * maxSize;			// 一份文件结束的位置
                getWrite(file, i, fbegin, fend);		// 写入每一份文件
            }

//            if (length - offset > 0) {
//                getWrite(file, num - 1, offset, length);  // 如果还有多的文件直接写入最后一份文件
//            }

            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param file
     * @param index
     * @param fbegin
     * @param fend
     * 合成视频
     */
    public static long getWrite(String file, int index, long fbegin, long fend) {
        long endPointer = 0;			// 每一份文件的结束偏移量
        try {
            RandomAccessFile in = new RandomAccessFile(new File(file), "r");  // 读取文件
            RandomAccessFile out = new RandomAccessFile(new File(file + "_" + index+"_.mp4"), "rw");  // 输出文件
            in.seek(fbegin);		// 定位偏移量
            byte[] b = new byte[1024];
            int n = 0;
            while(in.getFilePointer() < fend && (n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }

            endPointer = in.getFilePointer();

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return endPointer;
    }

    public static void merge(String file, String tempfile, int tempcount) {

        try {
            RandomAccessFile raf = new RandomAccessFile(new File(file), "rw");
            for (int i = 0; i < tempcount; i++) {
                RandomAccessFile reader = new RandomAccessFile(new File(tempfile + "_" + i), "r");
                byte[] b = new byte[1024];
                int temp = 0;
                while((temp = reader.read(b)) != -1) {
                    raf.write(b, 0, temp);
                }
                reader.close();
            }
            raf.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
