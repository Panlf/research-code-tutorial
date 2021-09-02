package com.plf.boot.unified.config;

import com.plf.boot.unified.interceptor.TranceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private TranceIdInterceptor tranceIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tranceIdInterceptor).addPathPatterns("/**").order(1);
    }
}
