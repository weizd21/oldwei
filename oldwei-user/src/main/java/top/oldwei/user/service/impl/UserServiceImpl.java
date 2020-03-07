package top.oldwei.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.oldwei.user.entity.User;
import top.oldwei.user.mapper.UserMapper;
import top.oldwei.user.service.UserService;

/**
 * @Author:weizd
 * @Date:20-3-7
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
