package com.my.springboot.springboot1.controller;

import com.my.springboot.springboot1.dto.UserDTO;
import com.my.springboot.springboot1.enums.UserEnum;
import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.utils.DataResultVOUtil;
import com.my.springboot.springboot1.vo.DataResultVO;
import com.my.springboot.springboot1.vo.SaveUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public DataResultVO login(HttpSession httpSession, String userName, String password){
        System.out.println(userName);
        System.out.println(password);
        if(userName == null || password == null){
            return DataResultVOUtil.error(1000,"用户名和密码不能为空");
        }

        UserDTO userDTO = userService.selectUserByName(userName);
        if(userDTO == null){
            return DataResultVOUtil.error(1001,"用户不存在");
        }

        if(!password.equals(userDTO.getPassword())){
            return DataResultVOUtil.error(1001,"用户名或者密码错误");
        }

        httpSession.setAttribute("user_name",userName);
        return DataResultVOUtil.success();
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user_name");
        return "user/login";
    }

    @GetMapping("/getUserInfo")
    @ResponseBody
    public DataResultVO userDetail(@RequestParam(value = "id") Integer id) {
        User user = userService.getUserInfo(id);
        return DataResultVOUtil.success(user);
    }

    @PostMapping("/addUser")
    @ResponseBody
    public DataResultVO addUser(@RequestBody @Valid SaveUserVo saveUserVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return DataResultVOUtil.error(1000,bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = userService.addUser(saveUserVo);
        if (result) {
            return DataResultVOUtil.success();
        } else {
            return DataResultVOUtil.error(UserEnum.ADDFAIL.getCode(),UserEnum.ADDFAIL.getMsg());
        }
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public DataResultVO updateUser(@RequestBody @Valid SaveUserVo saveUserVo,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return DataResultVOUtil.error(1000,bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            boolean result = userService.updateUser(saveUserVo);
            if(result){
                return DataResultVOUtil.success();
            }else{
                return DataResultVOUtil.error(UserEnum.UPDATEFAIL.getCode(),UserEnum.UPDATEFAIL.getMsg());
            }
        }catch (BusinessException e){
            return DataResultVOUtil.error(e.getErrCode(),e.getMessage());
        }
    }

    @GetMapping("/delUserById")
    @ResponseBody
    public DataResultVO delUserById(@RequestParam(value = "id") Integer id){
        Map<String,Object> result = new HashMap<>();
        if (id == null) {
            return DataResultVOUtil.error(1000,"参数错误");
        }

        try {
            boolean delResult = userService.delUserById(id);
            if(delResult){
                return DataResultVOUtil.success();
            }else{
                return DataResultVOUtil.error(UserEnum.DELFAIL.getCode(),UserEnum.DELFAIL.getMsg());
            }
        }catch (BusinessException e) {
            return DataResultVOUtil.error(e.getErrCode(),e.getMessage());
        }
    }
}
