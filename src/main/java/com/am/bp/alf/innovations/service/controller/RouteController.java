package com.am.bp.alf.innovations.service.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.am.bp.alf.innovations.service.BaseService;
import com.am.bp.alf.innovations.service.RoutingService;
import com.am.bp.alf.innovations.service.domain.json.api.RouteRequest;
import com.am.bp.alf.innovations.service.domain.json.api.RouteResponse;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(BaseService.Params.BASE_PATH)
@Api
public class RouteController {

    @Autowired
    private RoutingService routingService;

    @PostMapping(BaseService.Params.ROUTE)
    public RouteResponse get(RouteRequest routeRequest) throws InterruptedException, ExecutionException {
        // logger.info("Processing Request ", routeRequest);
        return routingService.getWinner(routeRequest);
    }

}
