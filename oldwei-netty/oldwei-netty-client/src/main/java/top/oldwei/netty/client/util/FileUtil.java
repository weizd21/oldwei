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
        long fileSize = file.length();
        RandomAccessFile randomAccessFile = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");

            InputStream inputStream = new FileInputStream(file);

            byte[] readBuffer = new byte[2048];

            while (inputStream.read(readBuffer) != -1){
                MD5.update(readBuffer);
            }


//            randomAccessFile =  new RandomAccessFile(file,"r");
//            MappedByteBuffer mapBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0, fileSize);
//            byte[] readBuffer = new byte[bufferSize];
//            int readLength;
//            for(int offset = 0;offset < file.length();offset += readLength ){
//                if(offset+bufferSize <= fileSize){
//                    readLength = bufferSize;
//                }else{
//                    readLength = (int) (fileSize-offset);
//                }
//                mapBuffer.get(readBuffer, 0, readLength);
//                MD5.update(readBuffer,0,readLength);
//            }

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

}
