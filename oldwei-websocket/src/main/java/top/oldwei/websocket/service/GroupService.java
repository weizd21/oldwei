package top.oldwei.websocket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.oldwei.websocket.entity.Group;

import java.util.List;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
public interface GroupService extends IService<Group> {

    /**
     * 通过用户id查询用户 加入的用户组列表信息
     * @param userId
     * @return
     */
    List<Group> listGroupByUserId(String userId);
}
