package com.example.study.controller.user;

import com.example.study.pojo.User;
import com.example.study.service.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 *
 * @author libo
 * @date 2019/12/8 19:41
 */
@RestController
public class UserController implements IUserApi {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @Override
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}
