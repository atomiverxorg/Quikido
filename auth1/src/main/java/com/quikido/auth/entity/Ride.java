package com.quikido.auth.entity;

import com.quikido.auth.model.RideStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerEmail;
    private String driverEmail;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    // Getters and Setters

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getFare() {
        return fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

}
