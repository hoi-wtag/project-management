package com.iq.ema.controller;

import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    public EmployeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){

        return (List<Employee>) employeeService.getAll();
    }


    // create employee restapi

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody  Employee employee){

        return employeeService.save(employee);
    }

    // get employee by id rest api

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        return ResponseEntity.ok(employee);
    }

    // update employee by id rest api

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody  Employee employeeDetails){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updateEmployee= employeeService.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));

        employeeService.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
