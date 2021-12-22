package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AreaAlreadyExistsException extends RuntimeException{
    public AreaAlreadyExistsException(String message) {
        super(message);
    }
}
