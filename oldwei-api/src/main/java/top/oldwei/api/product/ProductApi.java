package top.oldwei.api.product;

import top.oldwei.api.product.vo.ProductVO;
import top.oldwei.common.base.R;

/**
 * @Author:weizd
 * @Date:20-3-6
 */

public interface ProductApi {

    /**
     * 通过id查询商品信息
     * @param id
     * @return
     */
    ProductVO getProductById(String id);


}
