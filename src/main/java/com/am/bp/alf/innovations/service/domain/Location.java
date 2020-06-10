package com.am.bp.alf.innovations.service.domain;

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
