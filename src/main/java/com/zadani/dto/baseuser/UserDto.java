package com.zadani.dto.baseuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserDto", description = "Пользователь")
public class UserDto {

    @ApiModelProperty("Логин")
    private String username;

    @ApiModelProperty("Пароль")
    private String password;

    @ApiModelProperty("Электронная почта")
    private String email;

    @ApiModelProperty("Роли")
    private String roles;
}
