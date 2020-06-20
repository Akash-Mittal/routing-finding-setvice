package com.am.bp.innovations.service;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.am.bp.alf.innovations.service.application.RoutingApplicationTest;
import com.am.bp.innovations.domain.json.api.Destination;
import com.am.bp.innovations.domain.json.api.Origin;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.am.bp.innovations.domain.json.api.WayPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RoutingApplicationTest.class, OSRMService.class, RoutingService.class, })
@SpringBootTest
@Slf4j
@Ignore
public class RoutingServiceTest {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Autowired
    private OSRMService osrmService;

    @Autowired
    private RoutingService routingService;

    @Before
    public void setUp() throws Exception {
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
        // Rest of Validations
    }

    @Test
    public void testRoutingServiceInBudapest() throws Exception {
        ArrayList<WayPoint> wayPointList = getBudapestWayPoints();
        RouteRequest routeRequest = RouteRequest.builder()
                .origin(Origin.builder().lat(Double.valueOf("47.499649")).lon(Double.valueOf("19.047972")).build())
                .wayPoints(wayPointList)
                .destination(
                        Destination.builder().lat(Double.valueOf("47.500077")).lon(Double.valueOf("19.053063")).build())
                .build();
        log.info(gson.toJson(routeRequest));
        RouteResponse routeResponse = routingService.getWinner(routeRequest);
        Assertions.assertThat(routeResponse).isNotNull();
        // Rest of Validations

    }

    /*
     * Origin | Four Seasons Hotel |Budapest, Széchenyi István tér 5-6, 1051 | 47.499649, 19.047972
     * 
     * https://getlatlong.net/
     * 
     * Way Point 1 Raiffeisen Bank Headquarters | Budapest, Akadémia u. 6, 1054 | 47.502029,19.046440
     * 
     * Way Point 2 Las Vegas Casino |Address: Budapest, Széchenyi István tér 2, 1051 | 47.499544,19.047272
     * 
     * Way Point 3 Budapest Eye | | 47.498169, 19.052052
     * 
     * Destination St. Stephen's Basilica, Budapest, Szent István tér 1, 1051 | 47.500077, 19.053063
     * 
     */

    public ArrayList<WayPoint> getBudapestWayPoints() {
        ArrayList<WayPoint> wayPointList = new ArrayList<>();
        // Raiffeisen Bank Headquarters
        wayPointList.add(WayPoint.builder().lat(Double.valueOf("47.502029")).lon(Double.valueOf("19.046440")).build());
        // Las Vegas Casino
        wayPointList.add(WayPoint.builder().lat(Double.valueOf("47.499544")).lon(Double.valueOf("19.047272")).build());
        // Budapest Eye
        wayPointList.add(WayPoint.builder().lat(Double.valueOf("47.498169")).lon(Double.valueOf("19.052052")).build());
        return wayPointList;
    }
}
