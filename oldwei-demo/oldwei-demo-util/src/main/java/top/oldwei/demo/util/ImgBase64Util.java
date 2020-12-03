package top.oldwei.demo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author weizd
 * @date 2020/12/2下午4:26
 */
public class ImgBase64Util {

    public static String getImageStr(String imgFile) {
        byte[] data = null;
        try {
            data = Files.readAllBytes(Paths.get(imgFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return getImageStr(data);
    }


    public static String getImageStr(byte[] data) {
        // 加密
        return Base64.getEncoder().encodeToString(data);
    }



}
