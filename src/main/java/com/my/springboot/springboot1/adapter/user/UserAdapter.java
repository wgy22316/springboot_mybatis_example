package com.my.springboot.springboot1.adapter.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.utils.HttpClientUtil;

import java.util.HashMap;
import java.util.Map;

public class UserAdapter {

    public User getUserInfo(){
        Integer id = 11;
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        String url = "http://127.0.0.1:8080/user/getUserInfo";
        String result = HttpClientUtil.sendGet(url,params);
        JSONObject jsonObject = JSON.parseObject(result);
        Object object = jsonObject.get("data");
        String dataJson = (String)JSON.toJSON(object);
        User user = JSON.parseObject(dataJson,User.class);
        return user;
    }

    public static void main(String args[]){
        UserAdapter userAdapter = new UserAdapter();
        User user = userAdapter.getUserInfo();
        System.out.println(user.getUserName());
    }
}
