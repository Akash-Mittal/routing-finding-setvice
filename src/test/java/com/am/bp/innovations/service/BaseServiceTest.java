package com.am.bp.innovations.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.am.bp.innovations.domain.RouteWithWayPointName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Slf4j
public class BaseServiceTest implements BaseService.Routing {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    PodamFactoryImpl podamFactoryImpl = null;
    Gson gson = null;
    List<RouteWithWayPointName> list = null;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();
        podamFactoryImpl = new PodamFactoryImpl();
        gson = new GsonBuilder().setPrettyPrinting().create();
        list.add(podamFactoryImpl.manufacturePojo(RouteWithWayPointName.class));
        list.add(podamFactoryImpl.manufacturePojo(RouteWithWayPointName.class));
        list.add(podamFactoryImpl.manufacturePojo(RouteWithWayPointName.class));

    }

    @Test
    public void testsortRouteByDurationWhenNull() {
        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage("routesList is marked @NonNull but is null");
        sortAndGetFirst(null);
    }

    @Test
    public void testsortRouteByDuration() {
        list.get(0).getRoute().setDuration(231L);
        list.get(1).getRoute().setDuration(1567L);
        list.get(2).getRoute().setDuration(10L);
        String expectedWinner = list.get(2).getWayPointName();
        String actualWinnerName = sortAndGetFirst(list).getWinnerName();
        Assertions.assertThat(actualWinnerName).isEqualTo(expectedWinner);
    }

    @Test
    public void testGetRoute() {

    }

    @Test
    public void testGetRoutes() {

    }
}
