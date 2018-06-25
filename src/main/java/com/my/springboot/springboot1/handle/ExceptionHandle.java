package com.my.springboot.springboot1.handle;

import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.utils.DataResultVOUtil;
import com.my.springboot.springboot1.vo.DataResultVO;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public DataResultVO handle(Exception e){
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return DataResultVOUtil.error(businessException.getErrCode(),businessException.getMessage());
        }else if(e instanceof BindException) {
            return DataResultVOUtil.error(2001,e.getMessage());
        }else if(e instanceof MethodArgumentNotValidException) {
            return DataResultVOUtil.error(2000,e.getMessage());
        }else {
            return DataResultVOUtil.error(9999,e.getMessage());
        }
    }
}
