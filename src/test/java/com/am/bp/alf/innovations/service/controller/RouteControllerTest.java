package com.am.bp.alf.innovations.service.controller;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import com.am.bp.alf.innovations.service.application.RoutingApplicationTest;
import com.am.bp.innovations.domain.json.api.Destination;
import com.am.bp.innovations.domain.json.api.Origin;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.am.bp.innovations.domain.json.api.WayPoint;
import com.am.bp.innovations.domain.json.osrm.OSRMRouteResponse;
import com.am.bp.innovations.service.ComputationService;
import com.am.bp.innovations.service.OSRMService;
import com.am.bp.innovations.service.RoutingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingApplicationTest.class, OSRMService.class, RoutingService.class,
        ComputationService.class })
@SpringBootTest
public class RouteControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(RouteControllerTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Autowired
    private OSRMService osrmService;

    @Autowired
    private RoutingService routingService;

    @Before
    public void setup() {
    }

    @Test
    public void testOSRMService() throws Exception {
        OSRMRouteResponse osrmRouteResponse = osrmService
                .callRoutesAPI("13.388860,52.517037;13.397634,52.529407;13.428555,52.523219").get();
        logger.info("osrmRouteResponse {}", gson.toJson(osrmRouteResponse));
        Assertions.assertThat(osrmRouteResponse).isNotNull();

    }

    @Test
    public void testRoutingService() throws Exception {
        ArrayList<WayPoint> wayPointList = new ArrayList<>();

        wayPointList.add(WayPoint.builder().lat(Double.valueOf("50.058010")).lon(Double.valueOf("14.406775")).build());
        wayPointList.add(WayPoint.builder().lat(Double.valueOf("50.060757")).lon(Double.valueOf("14.431909")).build());
        wayPointList.add(WayPoint.builder().lat(Double.valueOf("50.078847")).lon(Double.valueOf("14.538084")).build());

        RouteRequest routeRequest = RouteRequest.builder()
                .origin(Origin.builder().lat(Double.valueOf("50.023226")).lon(Double.valueOf("14.439855")).build())
                .wayPoints(wayPointList).destination(Destination.builder().lat(Double.valueOf("50.121765629793295"))
                        .lon(Double.valueOf("14.489431312606477")).build())
                .build();
        RouteResponse routeResponse = routingService.getWinner(routeRequest);
        Assertions.assertThat(routeResponse).isNotNull();

    }

}