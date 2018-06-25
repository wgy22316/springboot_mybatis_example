package com.my.springboot.springboot1.respoonse;

import lombok.Data;

@Data
public class DataResult {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String msg;

    /**
     * 结果集
     */
    private Object data;


    public DataResult success(Object data){
        return null;
    }
}
