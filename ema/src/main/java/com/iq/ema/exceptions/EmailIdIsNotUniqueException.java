package com.iq.ema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class EmailIdIsNotUniqueException extends RuntimeException {
    private static final long serialVersionUID= 1L;

    public EmailIdIsNotUniqueException(String message){
        super(message);
    }

}
