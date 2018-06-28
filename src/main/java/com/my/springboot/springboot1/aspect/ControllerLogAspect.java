package com.my.springboot.springboot1.aspect;

import com.alibaba.fastjson.JSON;
import com.my.springboot.springboot1.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class ControllerLogAspect {

    private static final Logger logger =  LoggerFactory.getLogger(ControllerLogAspect.class);

    @Pointcut("execution(public * com.my.springboot.springboot1.controller..*.*(..))")
    public void apiLog() {

    }

    @Around("apiLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String clasName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object result = null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        //输入参数
        Map inputParam = request.getParameterMap();
        String requestMethod = request.getMethod();
        String ip = request.getRemoteAddr();
        Integer errCode = 0;
        String msg = "";
        Exception resultExeption = null;
        long startTime = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        }catch (Exception e){
            if (e instanceof BusinessException) {
                errCode = ((BusinessException) e).getErrCode();
                msg = e.getMessage();

            }

            resultExeption = e;
        }

        long runTime = System.currentTimeMillis() - startTime;
        String inputParamJson = JSON.toJSONString(inputParam);
        String reponseResult = JSON.toJSONString(result);
        logger.info("request_response=>requestUrl:{},queryString:{},requestParam:{},requestMethod:{},ip:{},errCode:{},msg:{},response:{},runTime:{}",
                requestUrl,
                queryString,
                inputParamJson,
                requestMethod,
                ip,
                errCode,
                msg,
                reponseResult,
                runTime
                );
        if (resultExeption != null) {
            throw resultExeption;
        }

        return result;

    }


}
