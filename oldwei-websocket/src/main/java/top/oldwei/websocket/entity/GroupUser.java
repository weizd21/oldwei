package top.oldwei.websocket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import top.oldwei.mybatis.entity.BaseEntity;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Data
@ToString(callSuper = true)
@TableName(value = "d_group_user")
public class GroupUser extends BaseEntity {

    private String groupId;

    private String userId;


}
