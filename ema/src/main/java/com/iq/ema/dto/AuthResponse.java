package com.iq.ema.dto;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 8317676219297719109L;

    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
