package com.plf.minio.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author panlf
 * @date 2021/12/7
 */
@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient
                .builder().endpoint("http://192.168.164.130:9000")
                .credentials("admin","12345678").build();

        return minioClient;
    }
}
