package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.study.mapper.IUserMapper;
import com.example.study.pojo.User;
import com.example.study.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * @author libo
 * @date 2019/12/8 18:09
 */
@Component
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public User login(String username, @NotNull(message = "密码不能为空") String password) {
        log.debug("开始调用用户登录[login],用户名：{}", username);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username)
                .eq("password", password);
        log.debug("完成调用登录方法[login]");
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String username, String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user != null) {
            //给出提示待处理 todo
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (userMapper.insert(user) > 0) {
            return user;
        }
        return null;
    }
}
