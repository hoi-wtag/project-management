package com.iq.ema.controller;
import com.iq.ema.model.AuthenticationBean;
import com.iq.ema.model.UserAccount;
import com.iq.ema.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserAccountService userAccountService;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @PostMapping(path="/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody @Valid UserAccount userAccount){
        userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
        UserAccount user=userAccountService.save(userAccount);
        return ResponseEntity.ok(user);
    }
    @GetMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationBean> authenticationBean() {
        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
        AuthenticationBean auth= new AuthenticationBean("You are authenticated");
        return ResponseEntity.ok(auth);
    }
}
