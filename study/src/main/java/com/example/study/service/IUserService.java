package com.example.study.service;

import com.example.study.pojo.User;

/**
 * @author libo
 * @date 2019/12/8 18:28
 */
public interface IUserService {
    /**
     * 登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User login(String username, String password);

    /**
     * 注册方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User register(String username, String password);

}
