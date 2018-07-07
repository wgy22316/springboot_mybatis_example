package com.my.springboot.springboot1.controller;

import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(ModelMap modelMap){
        List<User> userList = userService.queryUser();
        modelMap.addAttribute("userList",userList);
        return "index";
    }


}
