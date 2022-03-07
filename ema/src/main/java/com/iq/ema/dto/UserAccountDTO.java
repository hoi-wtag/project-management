package com.iq.ema.dto;

import lombok.Data;

@Data

public class UserAccountDTO {
    private long userId;
    private String userName;
    private String email;
    private String password;
}
