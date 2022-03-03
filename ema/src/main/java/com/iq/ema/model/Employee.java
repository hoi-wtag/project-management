package com.iq.ema.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@NamedEntityGraph(name = "employee.projects",attributeNodes =@NamedAttributeNode(value = "projects"))
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

    @ManyToMany(mappedBy="employees")
    @JsonIgnoreProperties("employees")
    private List<Project> projects;

    public Employee( String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

}
