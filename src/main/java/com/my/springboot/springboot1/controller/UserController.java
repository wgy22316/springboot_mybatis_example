package com.my.springboot.springboot1.controller;

import com.my.springboot.springboot1.enums.UserEnum;
import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.model.User;
import com.my.springboot.springboot1.service.UserService;
import com.my.springboot.springboot1.utils.DataResultVOUtil;
import com.my.springboot.springboot1.vo.DataResultVO;
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
    public DataResultVO userDetail(@RequestParam(value = "id") Integer id) {
//        try {
//            User user = userService.getUserInfo(id);
//            return DataResultVOUtil.success(user);
//        }catch (BusinessException e){
//            return DataResultVOUtil.error(e.getErrCode(),e.getMessage());
//        }
        //全局异常捕获
        User user = userService.getUserInfo(id);
        return DataResultVOUtil.success(user);
    }

    @PostMapping("/addUser")
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
