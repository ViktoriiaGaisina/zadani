package com.zadani.exception.exceptionhanding.customexception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel("Недопустимое сообщение об исключении")
public class NotValidExceptionMessage {

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @ApiModelProperty(name = "Дата ошибки")
    private final LocalDateTime timestamp;

    @ApiModelProperty(name = "Код ответа")
    private final int status;

    @ApiModelProperty(name = "Что за ошибка")
    private final String error;

    @ApiModelProperty(name = "Информация об ошибке")
    private final Map<String,String> message;

    @ApiModelProperty(name = "Адрес запроса")
    private final String path;
}
