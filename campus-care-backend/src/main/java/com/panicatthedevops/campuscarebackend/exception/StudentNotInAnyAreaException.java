package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentNotInAnyAreaException extends RuntimeException {
    public StudentNotInAnyAreaException(String message) {
        super(message);
    }
}
