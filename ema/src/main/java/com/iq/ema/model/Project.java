package com.iq.ema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @NotBlank(message="*Must give a  name")
    @Size(min=2, max=50)
    private String name;

    @NotBlank(message="*Must give a stage")
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS

    private String description;

    @ManyToMany(cascade = {CascadeType.DETACH,  CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )

    private List<Employee> employees;

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }
}
