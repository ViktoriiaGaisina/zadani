package com.zadani.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CryptoNotFoundException extends RuntimeException {

    public CryptoNotFoundException(String message) {
        super(message);
    }
}
