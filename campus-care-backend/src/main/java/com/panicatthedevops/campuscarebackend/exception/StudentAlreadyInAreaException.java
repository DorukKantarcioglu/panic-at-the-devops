package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentAlreadyInAreaException extends RuntimeException {
    public StudentAlreadyInAreaException(String message) {
        super(message);
    }
}
