package com.panicatthedevops.pandemicmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HesCodeNotValidException extends RuntimeException {
    public HesCodeNotValidException(String message) {
        super(message);
    }
}
