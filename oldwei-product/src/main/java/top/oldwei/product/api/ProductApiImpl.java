package top.oldwei.product.api;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import top.oldwei.api.product.ProductApi;
import top.oldwei.common.base.R;
import top.oldwei.product.mapper.ProductMapper;

/**
 * @Author:weizd
 * @Date:20-3-6
 */
@Service
public class ProductApiImpl implements ProductApi {

    @Autowired
    private ProductMapper productMapper;



    @Override
    public R getProductById(String id) {
        return R.success(productMapper.selectById(id));
    }
}
