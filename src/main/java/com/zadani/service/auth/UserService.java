package com.zadani.service.auth;


import com.zadani.dto.baseuser.UserDto;
import com.zadani.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> getByUsername(String username);

    Long createOrUpdateUser(UserDto request);
}
