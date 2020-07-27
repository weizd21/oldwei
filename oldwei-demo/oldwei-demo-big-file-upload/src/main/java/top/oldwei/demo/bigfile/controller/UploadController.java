package top.oldwei.demo.bigfile.controller;

import cn.hutool.core.date.SystemClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.oldwei.common.base.R;
import top.oldwei.demo.bigfile.dto.FileUploadDTO;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;

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


}
