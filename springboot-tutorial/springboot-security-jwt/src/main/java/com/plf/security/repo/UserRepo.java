package com.plf.security.repo;

import com.plf.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author panlf
 * @date 2023/5/4
 */
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
