package com.example.study.controller.user;

import com.example.study.pojo.User;
import com.example.study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 *
 * @author libo
 * @date 2019/12/8 19:41
 */
@RestController
public class UserController implements IUserApi {

    @Autowired
    private IUserService userService;

    @Override
    public User login(String username, String password) {
        return userService.login(username, password);
    }

    @Override
    public User register(String username, String password) {
        return userService.register(username, password);
    }
}
