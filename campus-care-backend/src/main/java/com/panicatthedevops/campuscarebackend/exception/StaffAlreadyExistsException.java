package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class StaffAlreadyExistsException extends RuntimeException {
    public StaffAlreadyExistsException(String message) {
        super(message);
    }
}
