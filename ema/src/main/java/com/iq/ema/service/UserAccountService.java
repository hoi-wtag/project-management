package com.iq.ema.service;
import com.iq.ema.model.UserAccount;
import com.iq.ema.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount userAccount) {

        return userAccountRepository.save(userAccount);
    }

}
