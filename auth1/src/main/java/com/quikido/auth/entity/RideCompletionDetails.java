package com.quikido.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RideCompletionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double actualDistance;
    private double actualDuration;

    private boolean isSurge;

    // Setter Getter


    public double getActualDistance() {
        return actualDistance;
    }

    public double getActualDuration() {
        return actualDuration;
    }

    public boolean isSurge() {
        return isSurge;
    }

    public void setActualDistance(double actualDistance) {
        this.actualDistance = actualDistance;
    }

    public void setActualDuration(double actualDuration) {
        this.actualDuration = actualDuration;
    }

    public void setSurge(boolean surge) {
        isSurge = surge;
    }
}
