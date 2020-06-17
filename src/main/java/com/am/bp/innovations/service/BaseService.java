package com.am.bp.innovations.service;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.validator.routines.InetAddressValidator;

import com.am.bp.innovations.domain.Location;
import com.am.bp.innovations.domain.json.api.Destination;
import com.am.bp.innovations.domain.json.api.Origin;
import com.am.bp.innovations.domain.json.api.WayPoint;

import lombok.NonNull;

public interface BaseService {

    interface Params {
        String API = "/api";
        String V1 = "/v1";
        String BASE_PATH = API + V1;
        String ROUTE = "/route";
        String API_OSRM_ROUTE = "http://router.project-osrm.org/route/v1/car/%s?geometries=geojson&overview=full";
        Double AVERAGE_RADIUS_OF_EARTH_KM = Double.valueOf(6371);
        Double AVERAGE_SPEED_CAR = Double.valueOf(40);

    }

    interface Predicates {
        Predicate<Object> CHECK_NOT_NULL_RETURN_TRUE = val -> val != null;
        Predicate<String> VALID_IP_ADDRESS = (input) -> InetAddressValidator.getInstance().isValid(input);
    }

    interface Functions {
        BiFunction<Double, Double, Double> SPEED = (distance, time) -> distance / time;
        BiFunction<Double, Double, Double> DISTANCE = (speed, time) -> speed * time;
        BiFunction<Double, Double, Double> TIME = (distance, speed) -> distance / speed;

        Function<Double, Double> AVERAGE_DISTANCE = (time) -> BaseService.Params.AVERAGE_SPEED_CAR * time;
        Function<Double, Double> AVERAGE_TIME = (distance) -> distance / BaseService.Params.AVERAGE_SPEED_CAR;

        public default Long calculateDistanceInKilometer(@NonNull Location origin, @NonNull Location destination) {

            Double latDistance = Math.toRadians(origin.getLatitude() - destination.getLatitude());
            Double lngDistance = Math.toRadians(origin.getLongitude() - destination.getLongitude());
            Double firstEquation = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(origin.getLatitude()))
                            * Math.cos(Math.toRadians(destination.getLatitude())) * Math.sin(lngDistance / 2)
                            * Math.sin(lngDistance / 2);
            Double secondEquation = 2 * Math.atan2(Math.sqrt(firstEquation), Math.sqrt(1 - firstEquation));
            return Long.valueOf(((Math.round(Params.AVERAGE_RADIUS_OF_EARTH_KM * secondEquation))));
        }

    }

    interface DTO {
        Function<Origin, Location> ORIGIN_LOCATION = (Origin) -> Location.builder().latitude(Origin.getLat())
                .longitude(Origin.getLon()).build();
        Function<WayPoint, Location> WAYPOINT_LOCATION = (wayPoint) -> Location.builder().latitude(wayPoint.getLat())
                .longitude(wayPoint.getLon()).build();
        Function<Destination, Location> DESTINATION_LOCATION = (destination) -> Location.builder()
                .latitude(destination.getLat()).longitude(destination.getLon()).build();
    }

}