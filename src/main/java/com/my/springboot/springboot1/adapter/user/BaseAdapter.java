package com.my.springboot.springboot1.adapter.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.utils.HttpClientUtil;

import java.util.Map;

public class BaseAdapter {

    public UserResultDTO sendGet(String url, Map<String, Object> params, Class objecClass){
        String result = HttpClientUtil.sendGet(url, params);
        if (result == null) {
            throw new BusinessException(9999,"result error");
        }

        JSONObject jsonObject = JSON.parseObject(result);
        Integer code = (Integer) jsonObject.get("code");
        if (code != null && code != 0) {
            throw new BusinessException((Integer) jsonObject.get("code"),(String) jsonObject.get("msg"));
        }

        Object resultData = jsonObject.getObject("data",objecClass);
        UserResultDTO userResultDTO = new UserResultDTO();
        userResultDTO.setCode(code);
        userResultDTO.setMsg((String) jsonObject.get("msg"));
        userResultDTO.setData(resultData);
        return userResultDTO;
    }

    public UserResultDTO sendPostJosn(String url, Map<String, Object> params, Class objecClass){
        String result = HttpClientUtil.sendPostJson(url, params);
        if (result == null) {
            throw new BusinessException(9999,"result error");
        }

        JSONObject jsonObject = JSON.parseObject(result);
        Integer code = (Integer) jsonObject.get("code");
        if (code != null && code != 0) {
            throw new BusinessException((Integer) jsonObject.get("code"),(String) jsonObject.get("msg"));
        }

        Object resultData = null;
        if(objecClass != null){
            resultData = jsonObject.getObject("data",objecClass);
        }

        UserResultDTO userResultDTO = new UserResultDTO();
        userResultDTO.setCode(code);
        userResultDTO.setMsg((String) jsonObject.get("msg"));
        userResultDTO.setData(resultData);
        return userResultDTO;
    }
}
