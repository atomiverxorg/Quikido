package com.quikido.auth.entity;

import com.quikido.auth.model.RideRequestStatus;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerEmail;
    private String driverEmail;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus status;

    private LocalDateTime requestTime;

    // Getters and Setters


    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public RideRequestStatus getStatus() {
        return status;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setStatus(RideRequestStatus status) {
        this.status = status;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
