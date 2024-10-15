package com.zadani.exception.exceptionhanding;

import com.zadani.exception.NotFoundException;
import com.zadani.exception.UnauthorizedUserException;
import com.zadani.exception.exceptionhanding.customexception.BadRequestException;
import com.zadani.exception.exceptionhanding.customexception.ExceptionMessage;
import com.zadani.exception.exceptionhanding.customexception.NotValidExceptionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();

        return new ResponseEntity<>(exceptionMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        NotValidExceptionMessage apiException = new NotValidExceptionMessage(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                errorMap,
                request.getContextPath()
        );
        return new ResponseEntity<>(apiException, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(exceptionMessage, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(exceptionMessage, status);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleApiRequestException(NotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleApiBadRequestException(BadRequestException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<Object> handleApiBadRequestException(UnauthorizedUserException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(e.getMessage())
                .path(request.getContextPath())
                .build();
        return new ResponseEntity<>(exceptionMessage, httpStatus);
    }
}
