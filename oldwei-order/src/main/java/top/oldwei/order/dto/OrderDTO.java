package top.oldwei.order.dto;

import lombok.Data;
import top.oldwei.api.product.dto.ProductIdNumDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Data
public class OrderDTO implements Serializable {

    /**
     *  商品信息
     */
    private List<ProductIdNumDTO> productList;


    private String addressId;




}
