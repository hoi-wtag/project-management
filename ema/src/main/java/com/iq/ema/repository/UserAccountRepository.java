package com.iq.ema.repository;

import com.iq.ema.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    public UserAccount findByEmail(String value);
    public UserAccount findByUserName(String value);
}
