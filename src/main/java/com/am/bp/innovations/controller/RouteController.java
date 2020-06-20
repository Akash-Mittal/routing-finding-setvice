package com.am.bp.innovations.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.bp.innovations.domain.BaseResponse;
import com.am.bp.innovations.domain.json.api.RouteRequest;
import com.am.bp.innovations.domain.json.api.RouteResponse;
import com.am.bp.innovations.service.AuthService;
import com.am.bp.innovations.service.BaseService;
import com.am.bp.innovations.service.RoutingService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(BaseService.Params.BASE_PATH)
@Api
public class RouteController {

    @Autowired
    private RoutingService routingService;

    @Autowired
    private AuthService authService;

    @PostMapping(BaseService.Params.ROUTE)
    public RouteResponse get(@RequestHeader(value = "x-secret") String secret, @RequestBody RouteRequest routeRequest)
            throws InterruptedException, ExecutionException {

        if (authService.validate(secret)) {
            RouteResponse routeResponse = routingService.getWinner(routeRequest);
            routeResponse.setBaseResponse(BaseResponse.builder().message("Request Completed Succesfully")
                    .httpStatus(HttpStatus.ACCEPTED).build());
            return routeResponse;

        }
        return RouteResponse.builder()
                .baseResponse(
                        BaseResponse.builder().message("Invalid Secret").httpStatus(HttpStatus.UNAUTHORIZED).build())
                .build();
    }

}
