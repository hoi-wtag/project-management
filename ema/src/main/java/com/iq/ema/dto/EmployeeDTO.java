package com.iq.ema.dto;
import lombok.Data;

import java.util.List;
@Data
public class EmployeeDTO extends EmployeeListDTO{
    private List<ProjectListDTO> projects;
}
