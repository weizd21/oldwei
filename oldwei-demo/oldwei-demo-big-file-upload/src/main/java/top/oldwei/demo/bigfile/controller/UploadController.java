package top.oldwei.demo.bigfile.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.oldwei.common.base.R;
import top.oldwei.demo.bigfile.dto.FileUploadDTO;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:weizd
 * @Date:20-7-25
 */
@Slf4j
@RestController
@RequestMapping("/chunk")
@CrossOrigin
public class UploadController {

    private String basePath = "/home/ap/aipf/chunk";

    private static long start = 0;


    private static Map<String,Long> uploadStartTimeMap = Maps.newConcurrentMap();


    @PostMapping("/v1/upload")
    public R chuckUploadV1(FileUploadDTO fileUploadDTO, MultipartFile file)throws Exception{

        if(fileUploadDTO.getChunkNumber() == 1){
            uploadStartTimeMap.put(fileUploadDTO.getIdentifier(),SystemClock.now()) ;
        }

        log.info("enter chuckUpload ..");

        log.info("fileUploadDTO: {}",fileUploadDTO);

        log.info("Chunk file size: {}",file.getSize());

        File targetFile = new File(Paths.get(basePath,fileUploadDTO.getRelativePath()).toString());
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        if(!targetFile.exists()){
            targetFile.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile,"rw");
        randomAccessFile.seek((fileUploadDTO.getChunkNumber()-1)*fileUploadDTO.getChunkSize());
        randomAccessFile.write(file.getBytes());
        randomAccessFile.close();

        byte[] bytes = new byte[1024];
        int length;
        while ((length = file.getInputStream().read(bytes)) != -1){

        }


        if(fileUploadDTO.getChunkNumber().intValue() == fileUploadDTO.getTotalChunks().intValue()){
            log.info("upload {} take {} ms",fileUploadDTO.getFilename(),SystemClock.now() - start);
        }

        return R.success();
    }



    @PostMapping("/upload")
    public R chuckUpload(FileUploadDTO fileUploadDTO, MultipartFile file)throws Exception{

        if(fileUploadDTO.getChunkNumber() == 1){
            start = SystemClock.now();
        }

        log.info("enter chuckUpload ..");

        log.info("fileUploadDTO: {}",fileUploadDTO);

        log.info("Chunk file size: {}",file.getSize());

        File targetFile = new File(Paths.get(basePath,fileUploadDTO.getRelativePath()).toString());
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        if(!targetFile.exists()){
            targetFile.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile,"rw");
        randomAccessFile.seek((fileUploadDTO.getChunkNumber()-1)*fileUploadDTO.getChunkSize());
        randomAccessFile.write(file.getBytes());
        randomAccessFile.close();


        if(fileUploadDTO.getChunkNumber().intValue() == fileUploadDTO.getTotalChunks().intValue()){
            log.info("upload {} take {} ms",fileUploadDTO.getFilename(),SystemClock.now() - start);
        }

        return R.success();
    }

    @PostMapping("/test")
    public R testUpload(String oldFilePath, MultipartFile file){
        long start = SystemClock.now();
        log.info("start to upload,time :{}",start);
        Map<String,String> result = new HashMap<>();
        try {
            InputStream inputStream = file.getInputStream();

            File target = new File("/Users/weizd/test/upload/"+ SystemClock.nowDate().replace(" ","")+"-" +file.getOriginalFilename());
            if(!target.exists()){
                target.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            byte[] bytes = new byte[1024];
            int length;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            Integer total = 0;
            while ((length = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes);
                for(int i=0;i<length;i++){
                    bos.write(bytes[i]);
                    if(bos.toString().contains("|@|")){
                        total++;
                        bos.reset();
                    }
                }
            }
            log.info("take time is : {}",SystemClock.now() - start);

            fileOutputStream.close();
            inputStream.close();
            result.put("total",total.toString());
            result.put("path",target.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }
        return R.success(result);
    }



}
