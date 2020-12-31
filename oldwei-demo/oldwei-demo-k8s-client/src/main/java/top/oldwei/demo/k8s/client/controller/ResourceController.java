package top.oldwei.demo.k8s.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: weizd
 * @time: 2020/12/12 1:34 上午
 */
@Slf4j
@RestController
@RequestMapping("/v1/k8s/resource")
public class ResourceController {



    @GetMapping("/test")
    public String test(){
        log.info("enter --------------------");
        return "success";
    }


}
