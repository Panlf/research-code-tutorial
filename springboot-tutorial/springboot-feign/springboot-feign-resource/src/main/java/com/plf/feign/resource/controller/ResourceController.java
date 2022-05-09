package com.plf.feign.resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panlf
 * @date 2022/5/9
 */
@RequestMapping("resource")
@RestController
public class ResourceController {


    @GetMapping("getResourceInfo")
    public String getResourceInfo(){
        return "you get it";
    }

    /*
    @GetMapping("getResourceInfoWithHeader")
    public String getResourceInfoWithHeader(@RequestHeader("token") String token){
       if(token !=null){
           return "token ===>"+token+",you get it";
       }
       return "you need token";
    }*/

    @GetMapping("getResourceInfoWithHeader")
    public String getResourceInfoWithHeader(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token !=null){
            return "token ===>"+token+",you get it";
        }
        return "you need token";
    }

    @GetMapping("getDelayResourceInfo")
    public String delayResourceInfo(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "delay info , you get it";
    }
}
