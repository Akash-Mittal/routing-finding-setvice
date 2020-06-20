
package com.am.bp.innovations.domain.json.osrm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "legs", "weight_name", "weight", "distance", "duration" })
@Data
public class Route {

    @JsonProperty("legs")
    private List<Leg> legs = null;
    @JsonProperty("weight_name")
    private String weightName;
    @JsonProperty("weight")
    private Double weight;
    @JsonProperty("distance")
    private Long distance;
    @JsonProperty("duration")
    private Long duration;

    @JsonProperty("legs")
    public List<Leg> getLegs() {
        return legs;
    }

    @JsonProperty("legs")
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    @JsonProperty("weight_name")
    public String getWeightName() {
        return weightName;
    }

    @JsonProperty("weight_name")
    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    @JsonProperty("weight")
    public Double getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @JsonProperty("distance")
    public Long getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    @JsonProperty("duration")
    public Long getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
