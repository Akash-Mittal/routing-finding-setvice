package com.am.bp.innovations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.bp.innovations.domain.Location;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.am.bp.innovations.domain.json.osrm.Route;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoutingService implements BaseService.DTO {

    @Autowired
    private Gson gson;

    @Autowired
    private OSRMService osrmService;

    public RouteResponse getWinner(@NonNull RouteRequest routeRequest) {
        List<Route> routesList = getRoutes(routeRequest);
        Preconditions.checkArgument(!CollectionUtils.isEmpty(routesList), "Routes Not Found!!");
        return RouteResponse.builder().build();
    }

    private Route getRoute(Location origin, Location waypoint, Location destination)
            throws InterruptedException, ExecutionException {
        String cordintaes = origin.getCommaSeperatedVal() + ";" + waypoint.getCommaSeperatedVal() + ";"
                + destination.getCommaSeperatedVal();
        log.info("Calling OSRM for origin {}  waypoint {} destination {}", origin, waypoint, destination);
        Route route = osrmService.callRoutesAPI(cordintaes).get().getRoutes().get(0);
        log.info("Response Recieved {}", gson.toJson(route));
        return route;
    }

    private List<Route> getRoutes(@NonNull RouteRequest routeRequest) {
        log.debug("Route Request {}", gson.toJson(routeRequest));
        List<Route> routesList = new ArrayList<>();
        routeRequest.getWayPoints().forEach(wayPoint -> {
            try {
                routesList.add(getRoute(ORIGIN_LOCATION.apply(routeRequest.getOrigin()),
                        WAYPOINT_LOCATION.apply(wayPoint), DESTINATION_LOCATION.apply(routeRequest.getDestination())));
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
            }
        });
        return routesList;
    }
}
