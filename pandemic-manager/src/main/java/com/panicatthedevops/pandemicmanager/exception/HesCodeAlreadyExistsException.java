package com.panicatthedevops.pandemicmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class HesCodeAlreadyExistsException extends RuntimeException {
    public HesCodeAlreadyExistsException(String message) {
        super(message);
    }
}
