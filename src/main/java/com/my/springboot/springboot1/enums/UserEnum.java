package com.my.springboot.springboot1.enums;

import lombok.Data;

public enum  UserEnum {

    NOTEXIST(1000,"用户不存在"),
    ADDFAIL(1001,"用户添加失败"),
    UPDATEFAIL(1002,"用户更新失败"),
    DELFAIL(1003,"用户删除失败")
    ;
    /**
     * 枚举code
     */
    private int code;

    /**
     * 信息
     */
    private String msg;

    UserEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
