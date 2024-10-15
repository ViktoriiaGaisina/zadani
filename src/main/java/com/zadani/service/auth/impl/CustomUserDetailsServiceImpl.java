package com.zadani.service.auth.impl;

import com.zadani.entity.auth.CustomUserDetails;
import com.zadani.service.auth.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserService baseUserService;

    public CustomUserDetailsServiceImpl(@Lazy UserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return baseUserService.getByUsername(username)
            .map(u -> CustomUserDetails.builder()
                .username(username)
                .password(u.getPassword())
                .grantedAuthorities(Arrays.stream(u.getRoles().split(";"))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                .build()
            ).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь не найдет с username = %s", username)));
    }
}
