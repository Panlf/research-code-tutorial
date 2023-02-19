package com.plf.boot.unified.service.impl;

import com.plf.boot.unified.bean.User;
import com.plf.boot.unified.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author panlf
 * @date 2023/2/19
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(User user) {
        System.out.println("保存当前用户到数据库，用户信息为："+user);
    }
}
