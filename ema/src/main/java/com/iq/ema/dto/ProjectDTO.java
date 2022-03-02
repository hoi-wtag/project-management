package com.iq.ema.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private long projectId;
    private String name;
    private String stage;
    private String description;
}
