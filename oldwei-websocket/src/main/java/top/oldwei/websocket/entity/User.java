package top.oldwei.websocket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import top.oldwei.mybatis.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Data
@ToString(callSuper = true)
@TableName(value = "d_user")
public class User extends BaseEntity {

    private String userCode;

    private String username;

    private String password;

    private String description;

    private String phone;

    private String mobile;

    private BigDecimal account;


}
