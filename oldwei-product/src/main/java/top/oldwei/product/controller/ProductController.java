package top.oldwei.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.redis.handler.JedisService;

import javax.annotation.Resource;

/**
 * @Author:weizd
 * @Date:20-3-2
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {


    @Resource
    private JedisService jedisService;


    @Autowired
    private RedissonClient redissonClient;


    @RequestMapping("/test1")
    public String testRedis1(){


        return jedisService.get("hello");

    }


    @RequestMapping("/test2")
    public String testRedis2(){
        jedisService.set("hello","helloWorld");

        System.out.println("get value: "+jedisService.get("hello"));

        RBucket<String> result = redissonClient.getBucket("hello");
        log.info("redissonClient.getList:【{}】",result.get());

        return "success";
    }


    @RequestMapping("/test3")
    public String testRedis3(){

        RBucket<String> result = redissonClient.getBucket("hello");
        log.info("redissonClient.getList:【{}】",result.get());

        return "success";
    }



}
