package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatAlreadyOccupiedException extends RuntimeException{
    public SeatAlreadyOccupiedException(String message) {
        super(message);
    }
}
