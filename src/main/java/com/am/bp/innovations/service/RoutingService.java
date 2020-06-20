package com.am.bp.innovations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bp.innovations.domain.RouteWithWayPointName;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.google.gson.Gson;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoutingService implements BaseService.Routing {

    @Autowired
    private Gson gson;

    @Autowired
    private OSRMService osrmService;

    public RouteResponse getWinner(@NonNull RouteRequest routeRequest) {
        List<RouteWithWayPointName> routesList = getRoutes(routeRequest, gson, osrmService);
        RouteResponse routeResponse = sortAndGetFirst(routesList);
        return routeResponse;
    }

}
