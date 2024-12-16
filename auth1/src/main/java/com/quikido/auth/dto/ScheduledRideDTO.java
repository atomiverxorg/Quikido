package com.quikido.auth.dto;

import java.time.LocalDateTime;

public class ScheduledRideDTO {
    private String passengerEmail;
    private double pickupLatitude;
    private double pickupLongitude;
    private LocalDateTime scheduledTime;

    // Getters and Setters


    public String getPassengerEmail() {
        return passengerEmail;
    }

    public double getPickupLatitude() {
        return pickupLatitude;
    }

    public double getPickupLongitude() {
        return pickupLongitude;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
