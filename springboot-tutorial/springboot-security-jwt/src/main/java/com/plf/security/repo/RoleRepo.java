package com.plf.security.repo;

import com.plf.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author panlf
 * @date 2023/5/4
 */
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
