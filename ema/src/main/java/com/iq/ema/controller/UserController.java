package com.iq.ema.controller;
import com.iq.ema.model.UserAccount;
import com.iq.ema.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
