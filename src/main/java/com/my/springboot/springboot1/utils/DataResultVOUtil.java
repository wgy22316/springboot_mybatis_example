package com.my.springboot.springboot1.utils;

import com.my.springboot.springboot1.vo.DataResultVO;

public class DataResultVOUtil {

    public static DataResultVO success(Object object) {
        DataResultVO dataResultVO = new DataResultVO();
        dataResultVO.setCode(0);
        dataResultVO.setMsg("success");
        dataResultVO.setData(object);
        return dataResultVO;
    }

    public static DataResultVO success() {
        return success(null);
    }

    public static DataResultVO error(Integer code, String msg) {
        DataResultVO dataResultVO = new DataResultVO();
        dataResultVO.setCode(code);
        dataResultVO.setMsg(msg);
        return dataResultVO;
    }
}
