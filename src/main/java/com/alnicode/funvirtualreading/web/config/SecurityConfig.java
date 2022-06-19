package com.alnicode.funvirtualreading.web.config;

import com.alnicode.funvirtualreading.constants.NationalityConstants;
import com.alnicode.funvirtualreading.constants.UserConstants;
import com.alnicode.funvirtualreading.web.config.jwt.JWTFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configure the application security.
 *
 * @author Alben Bustamante
 * @since 1.0
 * @version 1.0
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JWTFilterRequest filterRequest;

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
     * Define an {@link AuthenticationManager} bean.
     *
     * @param auth the auth configuration
     * @return the authentiation manager
     * @throws Exception if an error occurs
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
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
                .antMatchers(HttpMethod.GET, NationalityConstants.MAIN_PATH, NationalityConstants.MAIN_SORTED_PATH).permitAll()
                .antMatchers(HttpMethod.POST, UserConstants.MAIN_PATH, UserConstants.AUTH_PATH).permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/configuration/security").permitAll() //Swagger #1
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**").permitAll() //Swagger #2
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(filterRequest, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

}
