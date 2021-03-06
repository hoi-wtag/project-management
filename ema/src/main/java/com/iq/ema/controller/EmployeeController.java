package com.iq.ema.controller;

import com.iq.ema.dto.*;
import com.iq.ema.exceptions.EmailIdIsNotUniqueException;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



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

    @GetMapping("/employees/employeeSearchList")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeListDTO>> getEmployeesSearchList(@RequestParam String searchvalue){
        List<EmployeeListDTO> employeeSearch = employeeService.getSearchList(searchvalue).stream().map(employees -> modelMapper.map(employees, EmployeeListDTO.class))
                .collect(Collectors.toList());
        return  ResponseEntity.ok(employeeSearch);
    }


    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid Employee employee) throws Exception {
        Employee emp=employeeService.save(employee);
        EmployeeDTO employeeResponse=modelMapper.map(emp,EmployeeDTO.class);
        return ResponseEntity.ok(employeeResponse);
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable long id){
        Optional<Employee> employee= Optional.of(employeeService.findByEmployeeId(id).get());
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
        EmployeeDTO employeeResponse=modelMapper.map(employee,EmployeeDTO.class);
        return ResponseEntity.ok(employee);
    }

    // update employee by id rest api

    @PutMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable long id, @RequestBody  EmployeeDTO employeeDetails) throws Exception {
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
