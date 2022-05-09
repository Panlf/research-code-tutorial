package com.plf.feign.client.controller;

import com.plf.feign.client.service.FeignResourceApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2022/5/9
 */
@RestController
@RequestMapping("api")
public class FeignClientController {

    @Resource
    private FeignResourceApi feignResourceApi;

    @GetMapping("getInfo")
    public String getInfo(){
        String result = "";
        result = feignResourceApi.getResourceInfo();
        return result;
    }

    @GetMapping("getDelayInfo")
    public String getDelayInfo(){
        String result = "";
        result = feignResourceApi.getDelayResourceInfo();
        return result;
    }

    @GetMapping("getInfoWithHeader")
    public String getInfoWithHeader(){
        String result = "";
        result = feignResourceApi.getResourceInfoWithHeader();
        return result;
    }

}
