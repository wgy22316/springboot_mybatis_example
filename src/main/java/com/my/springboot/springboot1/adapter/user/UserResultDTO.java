package com.my.springboot.springboot1.adapter.user;

import lombok.Data;

@Data
public class UserResultDTO<T> {
    private Integer code;
    private String msg;
    private T data;
}
