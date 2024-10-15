package com.zadani.exception.exceptionhanding.customexception;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@ApiModel("Сообщение об исключении")
public class ExceptionMessage {

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @ApiModelProperty(name = "Дата ошибки")
    private LocalDateTime timestamp;

    @ApiModelProperty(name = "Код ответа")
    private int status;

    @ApiModelProperty(name = "Что за ошибка")
    private String error;

    @ApiModelProperty(name = "Информация об ошибке")
    private String message;

    @ApiModelProperty(name = "Адрес запроса")
    private String path;

}
