package com.panicatthedevops.campuscarebackend.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StudentSeatingPositionInvalidException extends RuntimeException{
    public StudentSeatingPositionInvalidException(String message) {
        super(message);
    }
}
