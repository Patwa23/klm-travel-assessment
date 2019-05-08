package com.afklm.exercises.authclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FareException  extends RuntimeException {
    public FareException(String message, Throwable cause) {
        super(message, cause);

    }

}
