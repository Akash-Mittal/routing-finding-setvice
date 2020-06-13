
package com.am.bp.innovations.domain.json.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Point X",
    "Point Y",
    "Point Z"
})
public class Delays {

    @JsonProperty("Point X")
    private Double pointX;
    @JsonProperty("Point Y")
    private Long pointY;
    @JsonProperty("Point Z")
    private Long pointZ;

    @JsonProperty("Point X")
    public Double getPointX() {
        return pointX;
    }

    @JsonProperty("Point X")
    public void setPointX(Double pointX) {
        this.pointX = pointX;
    }

    @JsonProperty("Point Y")
    public Long getPointY() {
        return pointY;
    }

    @JsonProperty("Point Y")
    public void setPointY(Long pointY) {
        this.pointY = pointY;
    }

    @JsonProperty("Point Z")
    public Long getPointZ() {
        return pointZ;
    }

    @JsonProperty("Point Z")
    public void setPointZ(Long pointZ) {
        this.pointZ = pointZ;
    }

}
