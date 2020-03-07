package top.oldwei.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import top.oldwei.api.product.dto.ProductIdNumDTO;
import top.oldwei.mybatis.entity.BaseEntity;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Data
@ToString(callSuper = true)
@TableName("d_order")
public class Order extends BaseEntity {


    private String oderNo;

    private String addressId;


}
