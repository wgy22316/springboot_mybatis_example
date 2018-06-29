package com.my.springboot.springboot1.adapter.user;

import com.my.springboot.springboot1.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserAdapter extends BaseAdapter{

    public void getUserInfo(){
        Integer id = 11;
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        String url = "http://127.0.0.1:8080/user/getUserInfo";

//        String result = HttpClientUtil.sendGet(url,params);
//        UserResultDTO<User> userResultDTO = JSON.parseObject(result,new TypeReference<UserResultDTO<User>>(){});
//        JSONObject jsonObject = JSON.parseObject(result);
//        jsonObject.getObject("code",User.class);
//        User user = jsonObject.getObject("data",User.class);
//        System.out.println(user.toString());
//        System.out.println(user.getUserName());

        UserResultDTO userResultDTO =  this.sendGet(url,params,User.class);
        User user = (User) userResultDTO.getData();
        System.out.println(user.getUserName());
    }

    public void addUser(){
        Map<String,Object> params = new HashMap<>();
        params.put("userName","哇哈哈哈");
        params.put("password","54321");
        String url = "http://127.0.0.1:8080/user/addUser";
        UserResultDTO userResultDTO = this.sendPostJosn(url, params,null);
        System.out.println(userResultDTO.getCode());
    }

    public static void main(String args[]){
        UserAdapter userAdapter = new UserAdapter();
        //userAdapter.getUserInfo();
        userAdapter.addUser();
    }
}
