package com.zadani.config;

import com.zadani.config.jwt.JwtConfig;
import com.zadani.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.SecureRandom;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    private final String[] PUBLIC_SWAGGER_RESOURCES = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**"
    };

    private final String[] PUBLIC_RESOURCES = {
            "/auth/login",
            "/registration/create",
            "/menu-info/dishes",
            "/menu-info/category/dish/{id}",
            "/menu-info/category/dish",
            "/menu-info/category",
            "/menu-info/category/{id}",
            "/menu-info/categoryes-dish",
            "/menu-info/{dishId}/image",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_SWAGGER_RESOURCES).permitAll()
                .antMatchers(PUBLIC_RESOURCES).permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .apply(new JwtConfig(jwtProvider, userDetailsService))
                .and()
                .build();
    }
}
