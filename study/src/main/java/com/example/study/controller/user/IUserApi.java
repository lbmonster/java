package com.example.study.controller.user;

import com.example.study.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * 用户相关接口
 *
 * @author libo
 * @date 2019/12/8 19:05
 */
@RequestMapping("/user")
@Api(tags = "用户相关接口文档")
public interface IUserApi {

    /**
     * 登录方法
     *
     * @param user 用户信息
     * @return 登录成功或失败
     * @author libo
     * @date 2019/12/8
     **/
    @ApiOperation("用户登录")
    @PostMapping("/login")
    User login(@Valid @RequestBody User user);

    /**
     * 注册方法
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    User register(@Valid @RequestBody User user);
}
