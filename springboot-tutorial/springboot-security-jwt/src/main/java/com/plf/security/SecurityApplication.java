package com.plf.security;

import com.plf.security.domain.Role;
import com.plf.security.domain.User;
import com.plf.security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author panlf
 * @date 2023/5/4
 */
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class,args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args ->{
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));


            userService.saveUser(new User(null,"Ana Blue","Ana","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Maybe Red","Maybe","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Ceb Orange","Ceb","1234",new ArrayList<>()));

            userService.saveRoleToUser("Ana","ROLE_ADMIN");
            userService.saveRoleToUser("Ana","ROLE_SUPER_ADMIN");
            userService.saveRoleToUser("Ana","ROLE_MANAGER");
            userService.saveRoleToUser("Maybe","ROLE_MANAGER");
            userService.saveRoleToUser("Ceb","ROLE_USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
