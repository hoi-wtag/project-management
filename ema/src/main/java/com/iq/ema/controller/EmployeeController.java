package com.iq.ema.controller;

import com.iq.ema.exceptions.EmailIdIsNotUniqueException;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/employees/{pageNumber}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<Employee>> getAllEmployeesWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<Employee> employeeWithPagination = employeeService.findEmployeeWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(employeeWithPagination);
    }


    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee){

        final Employee emp=employeeService.save(employee);
        if(emp != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(emp);
        else
            return ResponseEntity.badRequest().body(emp);
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        return ResponseEntity.ok(employee);
    }

    // update employee by id rest api

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody  Employee employeeDetails){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));

        final String oldemail=String.valueOf(employee.getEmailId());
        final String updatedemail=String.valueOf(employeeDetails.getEmailId());
        final String checkEmail= String.valueOf(employeeService.findByEmailId(updatedemail));
        if(oldemail.equals(updatedemail)==false && checkEmail == "null"){
            employee.setEmailId(employeeDetails.getEmailId());
        }
        if(oldemail.equals(updatedemail)==false && checkEmail != "null"){
            throw new EmailIdIsNotUniqueException("Provided Email  "+updatedemail+ " address already exist, please try again with different one");
        }
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());

        Employee updateEmployee= employeeService.save(employee);
        return ResponseEntity.ok(updateEmployee);
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
