package com.iq.ema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationBean {
    private String message;

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
}
