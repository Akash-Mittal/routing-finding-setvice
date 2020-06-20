
package com.am.bp.innovations.domain.json.api;

import com.am.bp.innovations.domain.BaseResponse;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class RouteResponse {

    private String winnerName;
    private Delays delays;
    private BaseResponse baseResponse;

}
