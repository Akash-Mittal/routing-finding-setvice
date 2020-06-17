package com.am.bp.innovations.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import com.am.bp.innovations.domain.Location;

public class BaseServiceTest implements BaseService.Functions {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCalculateDistanceInKilometer() throws Exception {

        Long distance = calculateDistanceInKilometer(
                Location.builder().latitude(Double.valueOf("50.023226")).longitude(Double.valueOf("14.439855")).build(),
                Location.builder().latitude(Double.valueOf("50.023226")).longitude(Double.valueOf("14.439855"))
                        .build());
        Assertions.assertThat(distance).isEqualTo(0L);
    }

}
