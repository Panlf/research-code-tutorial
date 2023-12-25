package com.plf.boot.unified.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.plf.boot.unified.blacklist.CountObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean
    public Cache<String,CountObject> countObjectCache(){
        return Caffeine.<String,CountObject>newBuilder()
                //设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(30, TimeUnit.MINUTES)
                //初始缓存容量
                .initialCapacity(100)
                //缓存最大条数
                .maximumSize(10000)
                .build();
    }


    @Bean
    public Cache<String,CountObject> blackListCache(){
        return Caffeine.<String, CountObject>newBuilder()
                //设置最后一次写入或访问后经过固定时间过期
                .expireAfterWrite(60, TimeUnit.SECONDS)
                //初始缓存容量
                .initialCapacity(100)
                //缓存最大条数
                .maximumSize(10000)
                .build();
    }
}
