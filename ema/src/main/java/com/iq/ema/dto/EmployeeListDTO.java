package com.iq.ema.dto;

import lombok.Data;

@Data
public class EmployeeListDTO {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String emailId;
}
