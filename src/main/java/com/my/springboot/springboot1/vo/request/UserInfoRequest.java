package com.my.springboot.springboot1.vo.request;

import com.my.springboot.springboot1.vo.BaseRequestVo;
import lombok.Data;

@Data
public class UserInfoRequest extends BaseRequestVo {

    private UserInfoVo data;
}
