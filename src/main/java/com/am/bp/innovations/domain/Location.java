package com.am.bp.innovations.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Location {
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;

    public String getCommaSeperatedVal() {
        return latitude + "," + longitude;
    }
}
