package com.iq.ema.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iq.ema.validators.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotBlank(message="*Must give a first name")
    @Size(min=2, max=50)
    private String firstName;

    @NotBlank(message="*Must give a last name")
    @Size(min=1, max=50)
    private String lastName;

    @NotBlank
    @Email(message="*Must be a valid email address")
    @UniqueValue
    private String emailId;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonIgnore
    private List<Project> projects;

    public Employee( String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

}
