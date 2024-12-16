package com.quikido.auth.dto;

public class FareDetails {
    private double baseFare;
    private double distanceCost;
    private double timeCost;
    private double totalFare;

    public FareDetails(double baseFare, double distanceCost, double timeCost, double totalFare) {
        this.baseFare = baseFare;
        this.distanceCost = distanceCost;
        this.timeCost = timeCost;
        this.totalFare = totalFare;
    }

    // Getters and Setters
}
