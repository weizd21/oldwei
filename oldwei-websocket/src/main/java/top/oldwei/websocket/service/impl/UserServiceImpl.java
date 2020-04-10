package top.oldwei.websocket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.oldwei.websocket.entity.User;
import top.oldwei.websocket.mapper.UserMapper;
import top.oldwei.websocket.service.UserService;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
