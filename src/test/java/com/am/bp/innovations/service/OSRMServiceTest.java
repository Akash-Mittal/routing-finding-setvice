package com.am.bp.innovations.service;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingApplicationTest.class, OSRMService.class, RoutingService.class, })
@SpringBootTest
@Slf4j
public class OSRMServiceTest {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Autowired
    private OSRMService osrmService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testOSRMService() throws Exception {
        OSRMRouteResponse osrmRouteResponse = osrmService
                .callRoutesAPI("13.388860,52.517037;13.397634,52.529407;13.428555,52.523219").get();
        log.info("osrmRouteResponse {}", gson.toJson(osrmRouteResponse));
        Assertions.assertThat(osrmRouteResponse).isNotNull();

    }

}
