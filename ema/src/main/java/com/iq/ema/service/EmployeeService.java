package com.iq.ema.service;

import com.iq.ema.model.Employee;
import com.iq.ema.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository empRepo;

    public Iterable<Employee> getAll() {

        return empRepo.findAll();
    }

    public Employee save(Employee employee) {

        return empRepo.save(employee);
    }

    public Optional<Employee> findByEmployeeId(long id) {
        // TODO Auto-generated method stub
        return empRepo.findById(id);
    }


    public void delete(Employee theEmp) {
        empRepo.delete(theEmp);

    }


}
