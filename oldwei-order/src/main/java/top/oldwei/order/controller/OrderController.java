package top.oldwei.order.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.api.product.ProductApi;
import top.oldwei.api.product.vo.ProductVO;
import top.oldwei.common.base.R;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {


    @Reference
    private ProductApi productApi;


    @GetMapping("/test1")
    public R test1(String id){
        if(StrUtil.isEmpty(id)){
            id = "xx";
        }
        R<ProductVO> productVOR = productApi.getProductById(id);

        log.info("productVO:[{}]",productVOR.getData());

        return productVOR;
    }

}
