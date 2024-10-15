package com.zadani.mappers.impl;

import com.zadani.dto.baseuser.UserDto;
import com.zadani.entity.UserEntity;
import com.zadani.mappers.user.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserDto user) {
        return UserEntity.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}
