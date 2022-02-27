package com.iq.ema.service;

import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.model.Employee;
import com.iq.ema.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;

    public Iterable<Employee> getAll() {

        return empRepo.findAll();
    }

    public Iterable<Employee> getAllById(List<Long> employees) {

        return empRepo.findAllById(employees);
    }

    public Page<Employee> findEmployeeWithPagination(int pageNumber,int pageSize){
        Page<Employee> employees = empRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC,"employeeId"));
        return  employees;
    }

    public Employee save(Employee employee) {

        return empRepo.save(employee);
    }

    public Employee findByEmailId(String emailId) {

        return empRepo.findByEmailId(emailId);
    }

    public Optional<Employee> findByEmployeeId(long id) {
        return empRepo.findById(id);
    }


    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);

    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

}
