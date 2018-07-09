package com.my.springboot.springboot1.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaveUserVo {

    private Integer id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 1男 2女
     */
    private Byte sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String mobile;

}
