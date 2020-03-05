package top.oldwei.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.common.base.R;
import top.oldwei.product.entity.Product;
import top.oldwei.product.service.ProductService;
import top.oldwei.redis.handler.JedisService;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @Author:weizd
 * @Date:20-3-2
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

//    @Resource
//    private JedisService jedisService;
//

    @Autowired
    private RedissonClient redissonClient;


//    @RequestMapping("/test1")
//    public String testRedis1(){
//
//
//        return jedisService.get("hello");
//
//    }
//
//
//    @RequestMapping("/test2")
//    public String testRedis2(){
//        jedisService.set("hello","helloWorld");
//
//        System.out.println("get value: "+jedisService.get("hello"));
//
//        RBucket<String> result = redissonClient.getBucket("hello");
//        log.info("redissonClient.getList:【{}】",result.get());
//
//        return "success";
//    }
//

    @RequestMapping("/test3")
    public String testRedis3(){

        RBucket<String> result = redissonClient.getBucket("hello");

        result.set("current:"+ LocalDate.now().toString());

        log.info("redissonClient.getList:【{}】",result.get());

        return "success";
    }


    @GetMapping("/add")
    public R add(){
        Product product = new Product();

        product.setName("test1");
        product.setDesc("desc1");

        product.setLeaveNum(9999L);


        productService.save(product);




        log.info("product:[{}]",product);


        return R.success();
    }




}
