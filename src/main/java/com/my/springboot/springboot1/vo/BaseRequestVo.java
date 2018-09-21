package com.my.springboot.springboot1.vo;

import lombok.Data;

@Data
public class BaseRequestVo {
    /**
     * 请求账号:appId
     */
    private String appId;

    /**
     * 请求唯一Id
     */
    private String requestId;

    /**
     * 时间戳
     */
    private long timestamp;

    /**
     * 语言 (eg: en-US, zh-CN)
     */
    private String language;

    /**
     * 时区(eg: GMT+8)
     */
    private String timeZone;

    /**
     * 签名值
     */
    private String sign;
}
