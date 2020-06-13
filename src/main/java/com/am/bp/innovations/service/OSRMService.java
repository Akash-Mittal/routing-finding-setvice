package com.am.bp.innovations.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.am.bp.innovations.domain.json.osrm.OSRMRouteResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OSRMService implements BaseService {

    @Autowired
    private final RestTemplate restTemplate;

    @Async
    public CompletableFuture<OSRMRouteResponse> callRoutesAPI(final String coordinate) {
        final String url = String.format(Params.API_OSRM_ROUTE, coordinate);
        log.info("Calling Host: {}", url);
        return CompletableFuture.completedFuture(restTemplate.getForObject(url, OSRMRouteResponse.class));
    }

    public OSRMService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
}
