package com.my.springboot.springboot1.vo;

import lombok.Data;

@Data
public class DataResultVO {

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

}
