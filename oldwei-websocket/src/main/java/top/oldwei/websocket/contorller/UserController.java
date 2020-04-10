package top.oldwei.websocket.contorller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oldwei.common.base.R;
import top.oldwei.websocket.dto.UserDTO;
import top.oldwei.websocket.entity.User;
import top.oldwei.websocket.service.UserService;

import javax.validation.Valid;

/**
 * @Author:weizd
 * @Date:20-3-25
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public R add(@Valid @RequestBody UserDTO userDTO){
        try{
            User user = new User();
            user.setUserCode(userDTO.getUserCode());
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            userService.save(user);
            log.info("user:【{}】",user);
            return R.success(user);
        }catch (Exception e){
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

    @GetMapping("/get")
    public R get(String userCode){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",userCode);
        User user = userService.getOne(queryWrapper);
        if(user != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserCode(user.getUserCode());
            userDTO.setUsername(user.getUsername());
            return R.success(userDTO);
        }
        return R.fail("用户不存在");
    }

    @GetMapping("/list")
    public R list(){
        return R.success(userService.list());
    }

}
