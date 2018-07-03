package com.my.springboot.springboot1.handle;

import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.utils.DataResultVOUtil;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Object handle(HttpServletRequest httpServletRequest,Exception e){
        if (isAjax(httpServletRequest)) {
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
        }else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errMsg",e.getMessage());
            modelAndView.addObject("url",httpServletRequest.getRequestURL());
            modelAndView.setViewName("error/exception");
            return modelAndView;
        }

    }

    /**
     * 判断请求是否为ajax请求
     */
    public static boolean isAjax(HttpServletRequest request){
        return(request.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(request.getHeader("X-Requested-With")));
    }
}
