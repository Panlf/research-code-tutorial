package com.plf.boot.filter.config;

import com.plf.boot.filter.config.interceptor.MyInterceptor;
import com.plf.boot.filter.config.interceptor.MyInterceptorNext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private MyInterceptor myInterceptor;
    @Resource
    private MyInterceptorNext myInterceptorNext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这里的Order起作用了
        registry.addInterceptor(myInterceptorNext).addPathPatterns("/**").order(2);
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").order(1);
    }
}
