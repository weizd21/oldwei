package top.oldwei.product.api;

import cn.hutool.core.bean.BeanUtil;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.oldwei.api.product.ProductApi;
import top.oldwei.api.product.vo.ProductVO;
import top.oldwei.product.entity.Product;
import top.oldwei.product.mapper.ProductMapper;

/**
 * @Author:weizd
 * @Date:20-3-6
 */
@Service
@Component
public class ProductApiImpl implements ProductApi {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ProductVO getProductById(String id) {
        Product product = productMapper.selectById(id);

        ProductVO productVO = new ProductVO();

        BeanUtil.copyProperties(product,productVO);

        return productVO;
    }


}
