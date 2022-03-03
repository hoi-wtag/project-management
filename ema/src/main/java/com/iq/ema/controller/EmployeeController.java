package com.iq.ema.controller;

import com.iq.ema.dto.*;
import com.iq.ema.exceptions.EmailIdIsNotUniqueException;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import com.iq.ema.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeListDTO> getAllEmployees(){

        return employeeService.getAll().stream().map(employees -> modelMapper.map(employees, EmployeeListDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/employees/{pageNumber}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<EmployeeListDTO>> getEmployeesWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<EmployeeListDTO> employeeWithPagination = employeeService.findEmployeeWithPagination(pageNumber, pageSize).
                map(employees -> modelMapper.map(employees, EmployeeListDTO.class));
        return  ResponseEntity.ok(employeeWithPagination);
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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        EmployeeDTO employeeResponse=modelMapper.map(employee,EmployeeDTO.class);
        return ResponseEntity.ok(employeeResponse);
    }

    // update employee by id rest api

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long id, @RequestBody  EmployeeDTO employeeDetails){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));

        final String oldemail=String.valueOf(employee.getEmailId());
        final String updatedemail=String.valueOf(employeeDetails.getEmailId());
        Employee checkEmail= employeeService.findByEmailId(updatedemail);
        if(oldemail.equals(updatedemail)==false && checkEmail == null){
            employee.setEmailId(employeeDetails.getEmailId());
        }
        if(oldemail.equals(updatedemail)==false && checkEmail != null){
            throw new EmailIdIsNotUniqueException("Provided Email  "+updatedemail+ " address already exist, please try again with different one");
        }
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());

        Employee updateEmployee= employeeService.save(employee);

        EmployeeDTO updatedResponse=modelMapper.map(updateEmployee,EmployeeDTO.class);
        return ResponseEntity.ok(updatedResponse);
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id){
        Employee employee= employeeService.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        employeeService.delete(employee);
        ApiResponse apiResponse = new ApiResponse("Employee deleted successfully with id:"+id);
        return ResponseEntity.ok(apiResponse);
    }
}
