package com.plf.stream.controller;

import com.plf.stream.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2022/1/8
 */
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("getData")
    public String getData(){
        userInfoService.findAllUserInfo();
        return "success";
    }

    @GetMapping("fetchData")
    public String fetchData(){
        userInfoService.dealUserInfo();
        return "success";
    }
}
