package top.oldwei.demo.thread.pipe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtil {


    public static void main(String[] args) throws Exception{

        String filePath = "/home/weizd/usd.txt";
        String filePath2 = "/home/weizd/usd2.txt";

        filePath = "/Users/weizd/test/data/usd.txt";

        long startTime = System.currentTimeMillis();
        writeFile(filePath);
//        writeFile2(filePath2);
//        readFile(filePath2);
        System.out.println((System.currentTimeMillis() - startTime)/1000 + " s");

    }


    public static void writeFile(String filePath) throws Exception{
        File file = new File(filePath);
        String baseString = "usd-business-xxxxxxxxxxxxxxxxxxx-";
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<1500000;i++){
            sb.append(baseString);
            sb.append(i);
            if(i!=1500000){
                sb.append("|@|");
            }
        }
        cn.hutool.core.io.FileUtil.appendUtf8String(sb.toString(),file);
    }


    public static void writeFile2(String filePath) throws Exception{
        File file = new File(filePath);
        String baseString = "usd-business-xxxxxxxxxxxxxxxxxxx-";
        List<String> stringList = new ArrayList<>();
        for(int i=0;i<1500000;i++){
            if(i!=1500000){
                stringList.add(baseString+i+"|@|");
            }else {
                stringList.add(baseString+i);
            }
        }
        cn.hutool.core.io.FileUtil.appendUtf8Lines(stringList,file);
    }


    public static void readFile(String filePath) throws Exception{

        Set<String> strings = new HashSet<>();

        File file = new File(filePath);

        RandomAccessFile rAccessFile = new RandomAccessFile(file,"r");;

        long start =0 ;

        long sliceSize = file.length();;

        int bufferSize = 1024*1024;

        byte[] readBuffer = new byte[bufferSize];


        MappedByteBuffer mapBuffer = rAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0, sliceSize);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        for(int offset=0;offset<sliceSize;offset+=bufferSize){
            int readLength;
            if(offset+bufferSize<=sliceSize){
                readLength = bufferSize;
            }else{
                readLength = (int) (sliceSize-offset);
            }
            int beforeOffset = mapBuffer.position();
            mapBuffer.get(readBuffer, 0, readLength);
            int afterOffset = mapBuffer.position();

            for(int i=0;i<readLength;i++){
                byte tmp = readBuffer[i];
//                if(tmp=='\n' || tmp =='\r' || tmp == '|@|'){
////                    log.info(bos.toString());
//                    strings.add(bos.toString());
//                    bos.reset();
//                }else{
//                    bos.write(tmp);
//                }
                bos.write(tmp);
                if(bos.toString().contains("|@|")){
                    String[] strs = bos.toString().split("\\|\\@\\|");
                    String businessId = strs[0];
                    strings.add(businessId.trim());
                    bos.reset();
                }
            }
        }
        if(bos.size()>0){
//                handle(bos.toByteArray());
//            log.info(bos.toString());

        }
//            cyclicBarrier.await();//测试性能用
        System.out.println(strings.size());

    }










}
