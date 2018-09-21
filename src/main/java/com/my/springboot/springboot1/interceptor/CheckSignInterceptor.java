package com.my.springboot.springboot1.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.my.springboot.springboot1.annotation.CheckSign;
import com.my.springboot.springboot1.config.RunEnvironmentConfig;
import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.utils.EncryptUtil;
import com.my.springboot.springboot1.utils.RequestSignUtil;
import com.my.springboot.springboot1.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static com.my.springboot.springboot1.utils.RequestUtil.checkAppId;

@Component
public class CheckSignInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CheckSignInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        CheckSign checkSignAnnotation = method.getAnnotation(CheckSign.class);
        RunEnvironmentConfig runEnvironmentConfig = new RunEnvironmentConfig();
        System.out.println(runEnvironmentConfig.getActive());
        //有@CheckSign 注解，需要认证
        if (checkSignAnnotation != null) {
            //参与必须的签名参数是否为空
            JSONObject requestJsonObject = RequestUtil.getJSONParam(request);

            if (RequestUtil.checkParam(requestJsonObject) == false) {
                throw new BusinessException(20000, "参数错误(缺少公共参数)");
            }

            //请求时间是否超时
            if (RequestUtil.checkTimestamp(requestJsonObject) == false) {
                throw new BusinessException(20001, "请求时间戳超时");
            }

            //appid 是否是分配的appid 同时获取appid对应的密钥
            String appKey = checkAppId(requestJsonObject);
            if (StringUtils.isEmpty(appKey)) {
                throw new BusinessException(20002, "非法appId");
            }

            if (!RequestSignUtil.checkSign(requestJsonObject, appKey)) {
                throw new BusinessException(20003, "签名错误");
            }
        }

        return super.preHandle(request, response, handler);
    }



}
