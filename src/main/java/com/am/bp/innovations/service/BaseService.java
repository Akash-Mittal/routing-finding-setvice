package com.am.bp.innovations.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.am.bp.innovations.domain.Location;
import com.am.bp.innovations.domain.RouteWithWayPointName;
import com.am.bp.innovations.domain.json.api.Destination;
import com.am.bp.innovations.domain.json.api.Origin;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.am.bp.innovations.domain.json.api.WayPoint;
import com.am.bp.innovations.domain.json.osrm.Route;
import com.google.gson.Gson;

import lombok.NonNull;

public interface BaseService {

    interface Params {
        String API = "/api";
        String V1 = "/v1";
        String BASE_PATH = API + V1;
        String ROUTE = "/route";
        String API_OSRM_ROUTE = "http://router.project-osrm.org/route/v1/car/%s?geometries=geojson&overview=full";

    }

    interface Routing {
        Logger log = LoggerFactory.getLogger(Routing.class);

        Function<Origin, Location> ORIGIN_LOCATION = (origin) -> {
            Objects.nonNull(origin);
            return Location.builder().latitude(origin.getLat()).longitude(origin.getLon()).build();
        };
        Function<WayPoint, Location> WAYPOINT_LOCATION = (wayPoint) -> {
            Objects.nonNull(wayPoint);
            return Location.builder().latitude(wayPoint.getLat()).longitude(wayPoint.getLon()).build();
        };
        Function<Destination, Location> DESTINATION_LOCATION = (destination) -> {
            Objects.nonNull(destination);
            return Location.builder().latitude(destination.getLat()).longitude(destination.getLon()).build();
        };

        public default RouteResponse sortAndGetFirst(@NonNull List<RouteWithWayPointName> routesList) {
            // Validate size
            Collections.sort(routesList, sortRouteByDuration);
            return RouteResponse.builder().winnerName(routesList.get(0).getWayPointName()).build();
        }

        public default List<RouteWithWayPointName> getRoutes(@NonNull RouteRequest routeRequest, @NonNull Gson gson,
                @NonNull OSRMService osrmService) {
            log.debug("Route Request {}", gson.toJson(routeRequest));
            List<RouteWithWayPointName> routesList = new ArrayList<>();
            routeRequest.getWayPoints().forEach(wayPoint -> {
                try {
                    Route route = getRoute(ORIGIN_LOCATION.apply(routeRequest.getOrigin()),
                            WAYPOINT_LOCATION.apply(wayPoint),
                            DESTINATION_LOCATION.apply(routeRequest.getDestination()), gson, osrmService);
                    if (route != null) {
                        routesList.add(
                                RouteWithWayPointName.builder().route(route).wayPointName(wayPoint.getName()).build());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    log.error(e.getMessage());
                    // Throw Business Exception
                }
            });

            return routesList;
        }

        public default Route getRoute(@NonNull Location origin, @NonNull Location waypoint,
                @NonNull Location destination, @NonNull Gson gson, @NonNull OSRMService osrmService)
                throws InterruptedException, ExecutionException {
            String coordinates = origin.getCommaSeperatedVal() + ";" + waypoint.getCommaSeperatedVal() + ";"
                    + destination.getCommaSeperatedVal();
            log.info("Calling OSRM for origin {}  waypoint {} destination {}", origin, waypoint, destination);
            Route route = osrmService.callRoutesAPI(coordinates).get().getRoutes().get(0);
            log.info("Response Recieved {}", gson.toJson(route));
            return route;
        }

        Comparator<RouteWithWayPointName> sortRouteByDuration = (RouteWithWayPointName you,
                RouteWithWayPointName me) -> {
            Objects.nonNull(you);
            Objects.nonNull(me);
            return you.getRoute().getDuration().compareTo(me.getRoute().getDuration());
        };

    }

}
