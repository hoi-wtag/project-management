package com.iq.ema.service;
import com.iq.ema.model.UserAccount;
import com.iq.ema.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAccountService implements UserDetailsService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount userAccount) throws Exception {
        UserAccount checkUserName = userAccountRepository.findByUserName(userAccount.getUserName());
        if(checkUserName!=null){
            throw new Exception("Username  Already exists, please try with another one");
        }
        UserAccount checkUserEmail = userAccountRepository.findByEmail(userAccount.getEmail());
        if(checkUserEmail!=null){
            throw new Exception("User Email Address  Already exists, please try with another one");
        }
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }
}
