package com.alnicode.funvirtualreading.web.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configure the application security.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Configuration
public class SecurityConfig {

    /**
     * Define a password encoder bean by default.
     *
     * @return a {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
