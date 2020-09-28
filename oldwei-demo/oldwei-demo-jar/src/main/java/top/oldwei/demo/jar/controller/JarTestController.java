package top.oldwei.demo.jar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:weizd
 * @Date:20-7-31
 */
@Slf4j
@RestController
@RequestMapping("/jar")
public class JarTestController {

    @Value("${applicationValue}")
    private String applicationValue;


    @RequestMapping("/test")
    public String jarTest(){
        log.info("applicationValue: {}",applicationValue);
        log.info("jar test method .....");
        return "jar success ";
    }




}
