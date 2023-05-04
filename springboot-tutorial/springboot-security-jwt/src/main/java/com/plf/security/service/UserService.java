package com.plf.security.service;

import com.plf.security.domain.Role;
import com.plf.security.domain.User;

import java.util.List;

/**
 * @author panlf
 * @date 2023/5/4
 */
public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void saveRoleToUser(String username,String roleName);

    User getUser(String username);

    List<User> getUsers();
}
