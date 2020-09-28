package top.oldwei.netty.client.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author:weizd
 * @Date:20-5-12
 */

@Component
@Slf4j
public class FileUtil {

    private int bufferSize = 1024*1024;

    /**
     * 获取文件的md5值
     * @param filePath
     * @return
     */
    public String getFileMd5(String filePath){
        File file = new File(filePath);
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");

            InputStream inputStream = new FileInputStream(file);

            byte[] readBuffer = new byte[2048];

            while (inputStream.read(readBuffer) != -1){
                MD5.update(readBuffer);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件指定位置开始往后指定大小的字节数组
     * @param filePath
     * @param startIndex
     * @param readSize
     * @return
     */
    public static byte[] getFileRangeByte(String filePath,long startIndex,int readSize){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(filePath,"r");
            randomAccessFile.seek(startIndex);
            byte[] bytes = new byte[readSize];
            int readLength = randomAccessFile.read(bytes);
            if(readLength == -1){
                return null;
            }
            if(readLength < readSize){
                byte[] copy = new byte[readLength];
                System.arraycopy(bytes, 0, copy, 0, readLength);
                return copy;
            }else {
                return bytes;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
     }


}
