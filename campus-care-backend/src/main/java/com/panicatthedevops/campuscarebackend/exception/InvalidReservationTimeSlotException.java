package com.panicatthedevops.campuscarebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidReservationTimeSlotException extends RuntimeException{
    public InvalidReservationTimeSlotException(String message) {
        super(message);
    }
}
