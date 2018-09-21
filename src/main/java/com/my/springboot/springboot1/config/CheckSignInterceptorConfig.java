package com.my.springboot.springboot1.config;

import com.my.springboot.springboot1.interceptor.CheckSignInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CheckSignInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckSignInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login","/static/**","*.html");
    }
}
