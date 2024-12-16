package com.quikido.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class ScheduledRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerEmail;

    private double pickupLatitude;
    private double pickupLongitude;

    private LocalDateTime scheduledTime;

    private String driverEmail; // Null until a driver is assigned
    private boolean isCompleted = false;

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

    public String getDriverEmail() {
        return driverEmail;
    }

    public boolean isCompleted() {
        return isCompleted;
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

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
