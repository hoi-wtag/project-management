package com.iq.ema.controller;
import com.iq.ema.model.AuthenticationBean;
import com.iq.ema.model.UserAccount;
import com.iq.ema.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @PostMapping(path="/register")
    public UserAccount createUserAccount(@RequestBody UserAccount userAccount){
        userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
        return userAccountService.save(userAccount);
    }
    @GetMapping(path = "/basicauth")
    public AuthenticationBean authenticationBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        return new AuthenticationBean("You are authenticated");
    }
}
