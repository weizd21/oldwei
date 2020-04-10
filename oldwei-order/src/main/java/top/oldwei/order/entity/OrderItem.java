package top.oldwei.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import top.oldwei.mybatis.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Data
@ToString(callSuper = true)
@TableName("d_order_item")
public class OrderItem extends BaseEntity {


    private String orderId;

    private String shopId;

    private String productId;

    private Long productNum;

    private BigDecimal price;

    private BigDecimal money;


}
