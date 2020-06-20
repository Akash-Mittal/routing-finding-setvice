package com.am.bp.innovations.domain;

import com.am.bp.innovations.domain.json.osrm.Route;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteWithWayPointName {
    private String wayPointName;
    private Route route;
}
