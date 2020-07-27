package top.oldwei.demo.util.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.common.base.R;

import java.time.LocalDateTime;

/**
 * @Author:weizd
 * @Date:20-7-27
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/test")
    public R test(){

        log.info("time: {}", LocalDateTime.now());
        return R.success();
    }


}
