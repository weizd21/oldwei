package top.oldwei.websocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.oldwei.websocket.entity.Group;
import top.oldwei.websocket.mapper.GroupMapper;
import top.oldwei.websocket.service.GroupService;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

}
