package com.my.springboot.springboot1.mapper;

import com.my.springboot.springboot1.model.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User selectUserById(Integer id);
}