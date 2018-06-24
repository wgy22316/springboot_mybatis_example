package com.my.springboot.springboot1.service.impl;

import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserInfo() throws Exception {
        User user = userService.getUserInfo(1);
        System.out.println(user.getUserName());
        Assert.assertEquals("hello",user.getUserName());
    }

    @Test
    public void queryUser() throws Exception {

    }

    @Test
    public void selectUserById() throws Exception {
        User user = userService.selectUserById(1);
        System.out.println(user.getUserName());
        Assert.assertEquals("hello",user.getUserName());
    }


}