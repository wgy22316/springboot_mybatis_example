package com.my.springboot.springboot1.service.impl;

import com.my.springboot.springboot1.mapper.UserMapper;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.vo.SaveUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> queryUser() {
        return userMapper.selectAll();
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public Integer addUser(SaveUserVo saveUserVo) {
        User user = new User();
        BeanUtils.copyProperties(saveUserVo,user);
        Integer result = userMapper.insertSelective(user);
        return user.getId();
    }
}
