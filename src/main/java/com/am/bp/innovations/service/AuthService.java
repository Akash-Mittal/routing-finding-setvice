package com.am.bp.innovations.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bp.innovations.repo.AuthRepository;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public boolean validate(String secret) {
        return Arrays.equals(authRepository.getSecret(), secret.toCharArray());
    }

}
