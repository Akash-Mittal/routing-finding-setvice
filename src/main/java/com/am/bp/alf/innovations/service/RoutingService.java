package com.am.bp.alf.innovations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;

import com.am.bp.alf.innovations.service.domain.Location;
import com.am.bp.alf.innovations.service.domain.json.api.RouteRequest;
import com.am.bp.alf.innovations.service.domain.json.api.RouteResponse;
import com.am.bp.alf.innovations.service.domain.json.osrm.Route;
import com.google.gson.Gson;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoutingService implements BaseService.DTO {

    @Autowired
    private Gson gson;

    @Autowired
    private OSRMService osrmService;

    public Route getRoute(Location origin, Location waypoint, Location destination)
            throws InterruptedException, ExecutionException {
        String cordintaes = origin.getCommaSeperatedVal() + ";" + waypoint.getCommaSeperatedVal() + ";"
                + destination.getCommaSeperatedVal();
        log.info("Calling OSRM for origin {}  waypoint {} destination {}", origin, waypoint, destination);
        Route route = osrmService.callRoutesAPI(cordintaes).get().getRoutes().get(0);
        log.info("Response Recieved {}", gson.toJson(route));
        return route;
    }

    public RouteResponse getWinner(@NonNull RouteRequest routeRequest) {
        log.debug("Route Request {}", gson.toJson(routeRequest));
        List<Route> routesList = new ArrayList<>();
        routeRequest.getWayPoints().forEach(wayPoint -> {
            try {
                routesList.add(getRoute(ORIGIN_LOCATION.apply(routeRequest.getOrigin()),
                        WAYPOINT_LOCATION.apply(wayPoint), DESTINATION_LOCATION.apply(routeRequest.getDestination())));
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            } catch (ExecutionException e) {
                log.error(e.getMessage());
            }
        });
        return RouteResponse.builder().winnerName("Ram Ram G").build();
    }
}
