package com.my.springboot.springboot1.adapter.user;

public class BaseUserVO {

    /**
     * 版本号
     */
    private String v;

    /**
     * appid
     */
    private String appid;

    /**
     * 方法
     */
    private String method;

    /**
     * 机器码(客户端唯一标识)
     */
    private String uuid;

    /**
     * 客户端来源 pc|android
     */
    private String platform;

    /**
     * 时间戳
     */
    private Long _timestamp;

    /**
     * 签名sign
     */
    private String sign;
}
