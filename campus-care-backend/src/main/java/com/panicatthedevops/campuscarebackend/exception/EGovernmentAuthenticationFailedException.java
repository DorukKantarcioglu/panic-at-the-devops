package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EGovernmentAuthenticationFailedException extends RuntimeException {
    public EGovernmentAuthenticationFailedException(String message) {
        super(message);
    }
}
