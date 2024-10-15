package com.zadani.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AuthRequestDto", description = "Модель авторизации")
public class AuthRequestDto {

    @ApiModelProperty("Логин")
    private String username;

    @ApiModelProperty("Пароль")
    private String password;
}
