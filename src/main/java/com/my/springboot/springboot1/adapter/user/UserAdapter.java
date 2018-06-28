package com.my.springboot.springboot1.adapter.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.my.springboot.springboot1.dto.UserDTO;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class UserAdapter {

    public void getUserInfo(){
        Integer id = 11;
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        String url = "http://127.0.0.1:8080/user/getUserInfo";
        String result = HttpClientUtil.sendGet(url,params);
        UserResultDTO<Object> userResultDTO = JSON.parseObject(result,new TypeReference<UserResultDTO<Object>>(){});
        Object object = userResultDTO.getData();
        System.out.println(userResultDTO.getCode());
        System.out.println(userResultDTO.getMsg());
        System.out.println(object.toString());
    }

    public static void main(String args[]){
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.getUserInfo();
    }
}
