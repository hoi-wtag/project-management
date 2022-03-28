package com.iq.ema.service;

import com.iq.ema.model.JwtBlacklist;
import com.iq.ema.repository.JwtBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtBlacklistService {

    @Autowired
    JwtBlacklistRepository jwtBlacklistRepo;

    public JwtBlacklist save(JwtBlacklist jwtBlacklist) throws Exception {
        return jwtBlacklistRepo.save(jwtBlacklist);
    }

    public Optional<JwtBlacklist> findByToken(String token) {

        return jwtBlacklistRepo.findByToken(token);
    }
}
