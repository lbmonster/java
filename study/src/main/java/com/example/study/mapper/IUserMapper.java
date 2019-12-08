package com.example.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.study.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author libo
 * @date 2019/12/8 18:26
 */
@Mapper
public interface IUserMapper extends BaseMapper<User> {
}
