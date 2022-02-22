package com.iq.ema.controller;

import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){

        return (List<Employee>) employeeService.getAll();
    }


    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody @Valid Employee employee){

        return employeeService.save(employee);
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        return employee;
    }

    // update employee by id rest api

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody  Employee employeeDetails){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        Employee updateEmployee= employeeService.save(employeeDetails);
        return updateEmployee;
    }

    @PatchMapping("/employees/{id}")
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody  Employee patchEmployee) {
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));

        if(patchEmployee.getEmailId() != null) {
            employee.setEmailId(patchEmployee.getEmailId());
        }
        if(patchEmployee.getFirstName() != null) {
            employee.setFirstName(patchEmployee.getFirstName());
        }
        if(patchEmployee.getLastName() != null) {
            employee.setLastName(patchEmployee.getLastName());
        }

        return employeeService.save(employee);

    }
    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));

        employeeService.delete(employee);
    }
}
