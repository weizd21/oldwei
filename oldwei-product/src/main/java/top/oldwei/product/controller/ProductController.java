package top.oldwei.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.redis.handler.RedisService;

/**
 * @Author:weizd
 * @Date:20-3-2
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private RedisService redisService;


    @RequestMapping("/test1")
    public String testRedis1(){
        return redisService.get("hello");
    }


    @RequestMapping("/test2")
    public String testRedis2(){
        redisService.set("hello","helloWorld");

        System.out.println("get value: "+redisService.get("hello"));

        return "success";
    }


}
