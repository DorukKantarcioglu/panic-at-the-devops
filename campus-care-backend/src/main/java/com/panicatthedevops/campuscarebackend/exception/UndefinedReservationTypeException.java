package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UndefinedReservationTypeException extends RuntimeException{
    public UndefinedReservationTypeException(String message) {
        super(message);
    }
}
