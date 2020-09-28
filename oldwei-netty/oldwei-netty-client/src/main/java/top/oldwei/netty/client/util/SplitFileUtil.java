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


    private int bufferSize = 1024*1024;

    /**
     * 默认的截取大小 100个字符
     */
    private int defaultSize = 100;

    /**
     * 默认的分隔符
     */
    private char defaultSeparator = '\n';


    /**
     * 通过指定大小 并以默认的分隔符 进行切分
     * @param filePath
     * @param size
     * @return
     */
    public List<String> splitFileBySize(String filePath,int size){
        return this.splitFileBySeparatorAndSize(filePath,defaultSeparator,size);
    }

    /**
     * 通过指定分隔符 并使用默认大小对文件内容进行切分
     * @param filePath
     * @param separator
     * @return
     */
    public List<String> splitFileBySeparator(String filePath,char separator){
        return this.splitFileBySeparatorAndSize(filePath,separator,defaultSize);
    }


    /**
     * 根据分隔符和大小对文件内容进行切分
     * @param filePath
     * @param separator
     * @param size
     * @return
     */
    public List<String> splitFileBySeparatorAndSize(String filePath,char separator,int size){
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
            int readLength;

            int bufferIndex = 0;
            for(int offset = 0;offset < file.length();offset += readLength ){
                if(offset+bufferSize <= fileSize){
                    readLength = bufferSize;
                }else{
                    readLength = (int) (fileSize-offset);
                }
                mapBuffer.get(readBuffer, bufferIndex, readLength);
                int countNum = 1;
                for(int i=0;i<readLength;i++){
                    byte tmp = readBuffer[i];
                    if( countNum > size ){
                        if(tmp=='\n' || tmp=='\r' || tmp == separator){
                            result.add(bos.toString());
                            bos.reset();
                            countNum = 1;
                            continue;
                        }
                    }
                    countNum++;
                    bos.write(tmp);
                }

            }

        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return result;
    }









}
