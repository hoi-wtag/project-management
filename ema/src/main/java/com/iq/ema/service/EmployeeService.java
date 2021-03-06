package com.iq.ema.service;

import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;

    public List<Employee> getAll() {

        return empRepo.findAll();
    }

    public List<Employee> getSearchList(String keyword) {
        return empRepo.findByKeyword(keyword);
    }

    public Iterable<Employee> getAllById(List<Long> employees) {

        return empRepo.findAllById(employees);
    }

    public Page<Employee> findEmployeeWithPagination(int pageNumber,int pageSize){
        Page<Employee> employees = empRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.Direction.ASC,"employeeId"));
        return  employees;
    }

    public Employee save(Employee employee) throws Exception {
        return empRepo.save(employee);
    }

    public Employee findByEmailId(String emailId) {

        return empRepo.findByEmailId(emailId);
    }

    public Optional<Employee> findByEmployeeId(long id) {
        return empRepo.findByEmployeeId(id);
    }


    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);

    }

    public List<EmployeeProject> employeeProjects() {
        return empRepo.employeeProjects();
    }

}
