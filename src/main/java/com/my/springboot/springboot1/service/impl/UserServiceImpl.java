package com.my.springboot.springboot1.service.impl;

import com.my.springboot.springboot1.dto.UserDTO;
import com.my.springboot.springboot1.enums.UserEnum;
import com.my.springboot.springboot1.exception.BusinessException;
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
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            throw new BusinessException(UserEnum.NOTEXIST.getCode(),UserEnum.NOTEXIST.getMsg());
        }
        return user;
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
    public boolean addUser(SaveUserVo saveUserVo) {
        User user = new User();
        BeanUtils.copyProperties(saveUserVo,user);
        int result = userMapper.insertSelective(user);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(SaveUserVo saveUserVo) {
        User user = userMapper.selectByPrimaryKey(saveUserVo.getId());
        if (user == null) {
            throw new BusinessException(UserEnum.NOTEXIST.getCode(),UserEnum.NOTEXIST.getMsg());
        }

        BeanUtils.copyProperties(saveUserVo,user);
        int affectRows = userMapper.updateByPrimaryKeySelective(user);
        if (affectRows > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delUserById(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            throw new BusinessException(UserEnum.NOTEXIST.getCode(),UserEnum.NOTEXIST.getMsg());
        }

        int affectRows = userMapper.deleteByPrimaryKey(id);
        if(affectRows > 0){
            return true;
        }else{
            return false;
        }
    }

//    @Override
////    public UserDTO selectUserByName(String userName) {
////        return userMapper.selectUserByName(userName);
////    }
}
