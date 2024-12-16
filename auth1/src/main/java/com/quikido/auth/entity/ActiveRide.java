package com.quikido.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class ActiveRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerEmail;

    private double currentLatitude;
    private double currentLongitude;

    private double dropLatitude;
    private double dropLongitude;

    private List activeRides = new ArrayList<>();

    private String driverEmail; // Null until a driver is assigned
    private boolean isCompleted = false;

    private String url;
    private Date duration;
    // Getters and Setters


    public String getPassengerEmail() {
        return passengerEmail;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public double getDropLatitude() {
        return dropLatitude;
    }

    public double getDropLongitude() {
        return dropLongitude;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Date getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public void setDropLatitude(double dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    public void setDropLongitude(double dropLongitude) {
        this.dropLongitude = dropLongitude;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getActiveRides() {
        return activeRides;
    }

    public void setActiveRides(List<String> activeRides) {
        this.activeRides = activeRides;
    }
}
