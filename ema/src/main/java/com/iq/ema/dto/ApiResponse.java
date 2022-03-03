package com.iq.ema.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String respone;
    @Override
    public String toString() {
        return String.format("%s", respone);
    }
}
