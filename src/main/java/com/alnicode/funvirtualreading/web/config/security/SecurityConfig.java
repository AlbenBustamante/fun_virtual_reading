package com.alnicode.funvirtualreading.web.config.security;

import com.alnicode.funvirtualreading.constants.UserConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    /**
     * Override the main security.
     *
     * @param http the security
     * @return a {@link SecurityFilterChain}
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, UserConstants.MAIN_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

}
