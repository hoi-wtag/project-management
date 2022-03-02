package com.iq.ema.controller;
import com.iq.ema.model.AuthRequest;
import com.iq.ema.model.AuthResponse;
import com.iq.ema.model.UserAccount;
import com.iq.ema.service.UserAccountService;
import com.iq.ema.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path="/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody @Valid UserAccount userAccount){
        userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
        UserAccount user=userAccountService.save(userAccount);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path="/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
        final String token= jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
// Basic Authentication endpoint
//    @GetMapping(path = "/login")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<AuthenticationBean> authenticationBean() {
//        //throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
//        AuthenticationBean auth= new AuthenticationBean("You are authenticated");
//        return ResponseEntity.ok(auth);
//    }
}
