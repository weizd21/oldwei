package top.oldwei.api.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Data
public class ProductIdNumDTO implements Serializable {

    private String shopId;

    private String productId;

    private Long num;


}
