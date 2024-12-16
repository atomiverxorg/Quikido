package com.quikido.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RouteDetails {
    private String distance;
    private String duration;
    private String polyline;

    public RouteDetails(String distance, String duration, String polyline) {
        this.distance = distance;
        this.duration = duration;
        this.polyline = polyline;
    }

    // Getters and Setters

}
