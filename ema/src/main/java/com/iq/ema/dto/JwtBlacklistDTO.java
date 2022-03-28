package com.iq.ema.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtBlacklistDTO {
    private long blackListId;
    private String token;
}
