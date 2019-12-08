package com.example.study.controller.user;

import com.example.study.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
     * @param username 用户名
     * @param password 密码
     * @return 登录成功或失败
     * @author libo
     * @date 2019/12/8
     **/
    @ApiOperation("用户登录")
    @PostMapping("/login")
    User login(@RequestParam(value = "username")
                  @Valid @NotNull(message = "用户名不能为空")
                          String username,
                  @Valid @NotNull(message = "密码不能为空")
                  @RequestParam(value = "password") String password);

    /**
     * 注册方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    User register(@RequestParam(value = "username")
                  @Valid @NotNull(message = "用户名不能为空")
                          String username,
                  @Valid @NotNull(message = "密码不能为空")
                  @RequestParam(value = "password")
                          String password);
}
