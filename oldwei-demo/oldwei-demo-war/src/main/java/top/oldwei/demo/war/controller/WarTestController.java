package top.oldwei.demo.war.controller;

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
@RequestMapping("/war")
public class WarTestController {

    @Value("${applicationValue}")
    private String applicationValue;


    @RequestMapping("/test")
    public String jarTest(){
        log.info("applicationValue: {}",applicationValue);
        log.info("war test method .....");
        return "war success ";
    }




}
