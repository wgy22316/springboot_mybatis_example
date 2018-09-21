package com.my.springboot.springboot1.interceptor;

import com.my.springboot.springboot1.annotation.CheckSign;
import com.my.springboot.springboot1.exception.BusinessException;
import com.my.springboot.springboot1.utils.EncryptUtil;
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

        //有@CheckSign 注解，需要认证
        if (checkSignAnnotation != null) {
            //参与必须的签名参数是否为空
            if (checkParam(request) == false) {
                throw new BusinessException(20000, "参数错误");
            }

            //请求时间是否超时
            if (checkTimestamp(request) == false) {
                throw new BusinessException(20001, "请求时间戳超时");
            }

            //appid 是否是分配的appid 同时获取appid对应的密钥
            String appKey = checkAppId(request);
            if (StringUtils.isEmpty(appKey)) {
                throw new BusinessException(20002, "非法appid");
            }

            if (!checkSign(request, appKey)) {
                throw new BusinessException(20003, "签名失败");
            }
        }

        return super.preHandle(request, response, handler);
    }

    private boolean checkParam(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getParameter("appid"))) {
            return false;
        }
        return true;
    }

    private boolean checkTimestamp(HttpServletRequest request) {
        int timestamp = Integer.parseInt(request.getParameter("timestamp"));
        long currentTimestamp = (System.currentTimeMillis() / 1000);

        if (Math.abs(currentTimestamp - timestamp) > 300) {
            return false;
        }
        return true;
    }

    private String checkAppId(HttpServletRequest request) {
        String appid = request.getParameter("appid");
        //map 代替
        Map<Integer, String> appidMap = new HashMap() {
            {
                put(1000, "cxC2HMc0gCm0Wk7qqEOCWS0h1FoH3b1z");
                put(1001, "yzZ07jfvfssmgpESGutG6y8NK8Xap31x");
            }
        };

        //从配置文件中获取
        String appKey = appidMap.get(appid);
        if (StringUtils.isEmpty(appKey)) {
            return "";
        }
        return appKey;
    }

    private boolean checkSign(HttpServletRequest request, String appKey) {
        Map<String, String> signMap = new TreeMap<>();
        signMap.put("appid", request.getParameter("appid"));
        signMap.put("requestId", request.getParameter("requestId"));
        signMap.put("timestamp", request.getParameter("timestamp"));
        signMap.put("language", request.getParameter("language"));
        signMap.put("timeZone", request.getParameter("timeZone"));
        String sign = request.getParameter("sign");
        String md5Sign = generatorSign((TreeMap) signMap, appKey);
        if (!sign.equals(md5Sign)) {
            return false;
        }
        return true;
    }

    private String generatorSign(TreeMap map, String appKey) {
        Set<Map.Entry<String, String>> set = map.entrySet();
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> vo : set) {
            sb.append(vo.getKey()).append("&").append(vo.getValue());
        }

        String originStr = sb.append("&").append(appKey).toString();
        String md5SignStr = EncryptUtil.md5Encrypt(originStr);
        return md5SignStr;
    }

}
