package com.my.springboot.springboot1.controller;

import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.vo.SaveUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody @Valid SaveUserVo saveUserVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        }

        try {
            Integer result = userService.updateUser(saveUserVo);
            if(result != null && result != 0){
                return "更新成功";
            }else{
                return "更新失败";
            }
        }catch (BusinessException e){
            return e.getMessage();
        }
    }

    @GetMapping("/delUserById")
    public Map delUserById(@RequestParam(value = "id") Integer id){
        Map<String,Object> result = new HashMap<>();
        if(id == null){
            result.put("result","用户不存在");
            return result;
        }

        boolean delResult = userService.delUserById(id);
        if(delResult){
            result.put("result","用户删除成功");
            return result;
        }else {
            result.put("result","用户删除失败");
            return result;
        }
    }
}
