package com.my.springboot.springboot1.service;

import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.vo.SaveUserVo;

import java.util.List;

public interface UserService {

    User getUserInfo(Integer id);

    List<User> queryUser();

    User selectUserById(Integer id);

    boolean addUser(SaveUserVo saveUserVo);

    boolean updateUser(SaveUserVo saveUserVo);

    boolean delUserById(Integer id);
}
