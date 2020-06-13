package com.am.bp.innovations.repo;

import org.springframework.stereotype.Service;

@Service
public class AuthRepository {

    public char[] getSecret() {
        return "Mileus".toCharArray();
    }

}
