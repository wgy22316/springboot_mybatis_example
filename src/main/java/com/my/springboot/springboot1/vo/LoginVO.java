package com.my.springboot.springboot1.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginVO {

    /**
     * 用户名
     */
    @NotNull
    private String userName;

    /**
     * 密码
     */
    @NotNull
    private String password;
}
