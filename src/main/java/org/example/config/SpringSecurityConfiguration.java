package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        // 创建基于内存的用户信息管理器
        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        // 使用userDetailsManager创建UserDetails对象，用于管理用户名、密码、用户权限等内容
        userDetailsManager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER")
                .build());
        return userDetailsManager;
    }

}
