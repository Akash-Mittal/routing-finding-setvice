package com.am.bp.alf.innovations.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.am.bp.alf.innovations.service.application.RoutingApplicationTest;
import com.am.bp.innovations.domain.json.osrm.OSRMRouteResponse;
import com.am.bp.innovations.service.OSRMService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RoutingApplicationTest.class)
@SpringBootTest
public class OSRMServiceTest {
    @Autowired
    private OSRMService osrmService;

    @Before
    public void setUp() {
    }

    @Test
    public void testCallRoutesAPI() throws InterruptedException, ExecutionException {
        CompletableFuture<OSRMRouteResponse> completableFuture = osrmService.callRoutesAPI("");
        Assertions.assertThat(completableFuture.get()).isNotNull();
    }

}
