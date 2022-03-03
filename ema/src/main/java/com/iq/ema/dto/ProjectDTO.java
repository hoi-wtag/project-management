package com.iq.ema.dto;

import com.iq.ema.model.Employee;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO extends ProjectListDTO{
    private List<EmployeeListDTO> employees;
}
