package com.quikido.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Driver")
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private boolean isAvailable = true;

    private double currentLatitude;
    private double currentLongitude;
    public String deviceToken;
    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setCurrentLatitude(double currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public void setCurrentLongitude(double currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
