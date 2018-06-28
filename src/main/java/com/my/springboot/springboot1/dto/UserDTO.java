package com.my.springboot.springboot1.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Integer id;

    /**
     * 用户名
     */
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
