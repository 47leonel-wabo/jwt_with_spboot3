package com.wbt.jwt_secure_app_spbt3.config;

import com.wbt.jwt_secure_app_spbt3.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {
    private final UserRepository mUserRepository;

    public ApplicationConfig(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> mUserRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
