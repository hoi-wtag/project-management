package com.iq.ema.validators;

import com.iq.ema.model.Employee;
import com.iq.ema.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue,String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("start validation");
        Employee emp= empRepo.findByEmailId(s);
        if(emp != null)
            return false;
        else
            return true;
    }
}
