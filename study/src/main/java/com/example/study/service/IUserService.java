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
     * @param user 登录信息
     * @return 用户信息
     */
    User login(User user);

    /**
     * 注册方法
     *
     * @param user 用户信息
     * @return 用户信息
     */
    User register(User user);

}
