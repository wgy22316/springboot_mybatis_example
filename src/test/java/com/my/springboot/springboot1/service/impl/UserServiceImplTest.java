package com.my.springboot.springboot1.service.impl;

import com.alibaba.fastjson.JSON;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.utils.RequestSignUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.FileSystemNotFoundException;
import java.util.TreeMap;

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

    @Test
    public void testGetUserInfo(){
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("appid", "1000");
        treeMap.put("requestId", "123456789");
        treeMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        treeMap.put("language", "zh-CN");
        treeMap.put("timeZone", "GMT+8");

        String sign = RequestSignUtil.generatorSign(treeMap,"cxC2HMc0gCm0Wk7qqEOCWS0h1FoH3b1z");
        treeMap.put("sign", sign);
        System.out.println(JSON.toJSONString(treeMap));
    }

}