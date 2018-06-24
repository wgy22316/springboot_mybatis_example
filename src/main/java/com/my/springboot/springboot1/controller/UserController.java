package com.my.springboot.springboot1.controller;

import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.vo.SaveUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserInfo")
    public User userDetail(@RequestParam(value = "id") Integer id){
        return userService.getUserInfo(id);
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody @Valid SaveUserVo saveUserVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return userService.addUser(saveUserVo);
    }
}
