package com.plf.feign.client.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author panlf
 * @date 2022/5/9
 */
@Component
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    // 重试机制，慎重，需要考虑接口幂等性
    @Bean
    Retryer feignRetryer() {
        return new Retryer.Default();
    }

}
