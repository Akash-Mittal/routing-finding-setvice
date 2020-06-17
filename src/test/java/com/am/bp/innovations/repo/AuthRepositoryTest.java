package com.am.bp.innovations.repo;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class AuthRepositoryTest {
    private AuthRepository authRepository;

    @Before
    public void setUp() throws Exception {
        authRepository = new AuthRepository();
    }

    @Test
    public void testAuthRepository() {
        Assertions.assertThat(authRepository.getSecret()).isEqualTo("Mileus".toCharArray());
    }

}
