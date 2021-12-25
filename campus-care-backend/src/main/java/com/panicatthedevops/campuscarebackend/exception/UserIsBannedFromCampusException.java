package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserIsBannedFromCampusException extends RuntimeException{
    public UserIsBannedFromCampusException(String message) {
        super(message);
    }
}
