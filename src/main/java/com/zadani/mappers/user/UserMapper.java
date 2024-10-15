package com.zadani.mappers.user;


import com.zadani.dto.baseuser.UserDto;
import com.zadani.entity.UserEntity;

public interface UserMapper {

    UserEntity toEntity(UserDto baseUserDto);
}
