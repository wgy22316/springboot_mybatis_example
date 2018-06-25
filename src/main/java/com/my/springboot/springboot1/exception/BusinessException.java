package com.my.springboot.springboot1.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private Integer errCode;

    private String message;

    public BusinessException(Integer errCode,String message){
        this.errCode = errCode;
        this.message = message;
    }
}
