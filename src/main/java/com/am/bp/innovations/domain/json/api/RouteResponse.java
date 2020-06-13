
package com.am.bp.innovations.domain.json.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "winnerName", "delays" })
@Builder
public class RouteResponse {

    @JsonProperty("winnerName")
    private String winnerName;
    @JsonProperty("delays")
    private Delays delays;

    @JsonProperty("winnerName")
    public String getWinnerName() {
        return winnerName;
    }

    @JsonProperty("winnerName")
    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    @JsonProperty("delays")
    public Delays getDelays() {
        return delays;
    }

    @JsonProperty("delays")
    public void setDelays(Delays delays) {
        this.delays = delays;
    }

}
