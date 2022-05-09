package com.plf.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author panlf
 * @date 2022/5/9
 */
@FeignClient(name = "feign-resource",url = "http://localhost:8001")
public interface FeignResourceApi {

    @GetMapping("/resource/getResourceInfo")
    String getResourceInfo();

    @GetMapping("/resource/getDelayResourceInfo")
    String getDelayResourceInfo();

    @GetMapping("/resource/getResourceInfoWithHeader")
    String getResourceInfoWithHeader();

}
