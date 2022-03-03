package com.iq.ema.dto;

import com.iq.ema.model.Employee;
import lombok.Data;

import java.util.List;

@Data
public class ProjectListDTO {
    private long projectId;
    private String name;
    private String stage;
    private String description;

}
