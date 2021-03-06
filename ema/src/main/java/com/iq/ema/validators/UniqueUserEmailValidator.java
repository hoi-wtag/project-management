package com.iq.ema.validators;

import com.iq.ema.model.UserAccount;
import com.iq.ema.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail,String> {

    @Autowired
    UserAccountRepository userRepo;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        UserAccount user= userRepo.findByEmail(s);
        if(user != null)
            return false;
        else
            return true;
    }
}
