package top.oldwei.websocket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.oldwei.websocket.entity.Group;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Repository
public interface GroupMapper extends BaseMapper<Group> {


    @Select("select d_group.* from d_group where d_group.id in ( select group_id from d_group_user where user_id = #{userId})")
    List<Group> selectGroupByUserId(String userId);

}
