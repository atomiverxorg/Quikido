package com.quikido.auth.dto;

import jakarta.persistence.*;


@Entity
public class RideRequestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerEmail;
    private double pickupLongitude;
    private double pickupLatitude;

    // Setter and Getter

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public double getPickupLongitude() {
        return pickupLongitude;
    }

    public double getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }
}
