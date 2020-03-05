package top.oldwei.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.oldwei.mybatis.entity.BaseEntity;

/**
 * @Author:weizd
 * @Date:20-3-5
 */
@Data
@TableName(value = "d_product")
public class Product extends BaseEntity {

    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品描述
     */
    private String desc;

    /**
     * 剩余数量
     */
    private Long leaveNum;


}
