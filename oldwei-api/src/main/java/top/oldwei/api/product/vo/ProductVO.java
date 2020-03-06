package top.oldwei.api.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-6
 */
@Data
public class ProductVO implements Serializable {


    private String id;

    private String name;

    private String description;

    private Long leaveNum;


}
