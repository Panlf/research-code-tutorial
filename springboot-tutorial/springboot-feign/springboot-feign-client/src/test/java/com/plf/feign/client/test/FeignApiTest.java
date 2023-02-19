package com.plf.feign.client.test;

import com.plf.feign.client.service.FeignResourceApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2023/2/19
 */
@SpringBootTest
//@EnableFeignClients(basePackages = "com.plf.feign.client")
public class FeignApiTest {

    @Resource
    private FeignResourceApi feignResourceApi;

    @Test
    public void getResourceInfoWithParams(){
        System.out.println(feignResourceApi.getResourceWithParams("Hate"));
    }
}
