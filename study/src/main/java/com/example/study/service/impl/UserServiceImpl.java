package com.example.study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.study.mapper.IUserMapper;
import com.example.study.pojo.User;
import com.example.study.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author libo
 * @date 2019/12/8 18:09
 */
@Component
@Slf4j
public class UserServiceImpl implements IUserService {

    private final IUserMapper userMapper;

    public UserServiceImpl(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(User user) {
        log.debug("开始调用用户登录[login],用户名：{}", user.getUsername());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername())
                .eq("password", user.getPassword());
        log.debug("完成调用登录方法[login]");
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(User user) {
        if (user != null) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("username", user.getUsername());
            User localUser = userMapper.selectOne(userQueryWrapper);
            if (localUser != null) {
                //给出提示待处理 todo
            }
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setPassword(user.getPassword());
            if (userMapper.insert(newUser) > 0) {
                return newUser;
            }
        }
        return null;
    }
}
