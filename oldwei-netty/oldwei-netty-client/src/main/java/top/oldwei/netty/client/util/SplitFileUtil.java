package top.oldwei.netty.client.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-5-8
 */
@Slf4j
@Component
public class SplitFileUtil {


    private int defaultBufferSize = 50;


    /**
     * 根据分隔符和大小对文件内容进行切分
     * @param filePath
     * @param separator
     * @param bufferSize
     * @return
     */
    public List<String> splitFileBySeparatorAndSize(String filePath,String separator,int bufferSize){
        List<String> result = Lists.newArrayList();
        File file = new File(filePath);
        if(!file.exists()){
            log.warn("文件{}不存在",filePath);
            return result;
        }
        long fileSize = file.length();
        RandomAccessFile randomAccessFile = null;
        try{
            randomAccessFile =  new RandomAccessFile(file,"r");

            MappedByteBuffer mapBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0, fileSize);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] readBuffer = new byte[bufferSize];
            int readLength = bufferSize;
            for(int offset = 0;offset < file.length();offset += readLength ){
                if(offset+bufferSize <= fileSize){
                    readLength = bufferSize;
                }else{
                    readLength = (int) (fileSize-offset);
                }
                mapBuffer.get(readBuffer, 0, readLength);
                for(int i=0;i<readLength;i++){
                    byte tmp = readBuffer[i];
                    if(tmp=='\n' || tmp=='\r'){
                        log.info(bos.toString());
                        bos.reset();
                    }else{
                        bos.write(tmp);
                    }
                }

            }



//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            for(int offset=0;offset<sliceSize;offset+=bufferSize){
//                int readLength;
//                if(offset+bufferSize<=sliceSize){
//                    readLength = bufferSize;
//                }else{
//                    readLength = (int) (sliceSize-offset);
//                }
//                mapBuffer.get(readBuffer, 0, readLength);
//                for(int i=0;i<readLength;i++){
//                    byte tmp = readBuffer[i];
//                    if(tmp=='\n' || tmp=='\r'){
////                        handle(bos.toByteArray());
//
//                        log.info(bos.toString());
//
//                        bos.reset();
//                    }else{
//                        bos.write(tmp);
//                    }
//                }
//            }
//


        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return result;
    }









}
