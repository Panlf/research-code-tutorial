package com.plf.boot.unified.service;

import com.plf.boot.unified.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author panlf
 * @date 2023/2/19
 */
@SpringBootTest
@Slf4j
public class TestUserService {

    @Resource
    public UserService userService;

    @Test
    public void addUser(){
        log.info("test add user start");
        User user = User.builder()
                .userName("NaNa")
                .realName("娜娜")
                .build();
        userService.addUser(user);
        log.info("test add user end");
    }
}
