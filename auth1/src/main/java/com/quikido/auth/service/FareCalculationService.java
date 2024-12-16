package com.quikido.auth.service;

import com.quikido.auth.dto.FareDetails;
import org.springframework.stereotype.Service;

@Service
public class FareCalculationService {

    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;
    private static final double PER_MIN_RATE = 2.0;
    private static final double SURGE_MULTIPLIER = 1.5; // Example for high demand

    public FareDetails calculateFare(double distanceKm, double durationMinutes, boolean isSurge) {
        double baseFare = BASE_FARE;
        double distanceCost = distanceKm * PER_KM_RATE;
        double timeCost = durationMinutes * PER_MIN_RATE;

        double totalFare = baseFare + distanceCost + timeCost;

        if (isSurge) {
            totalFare *= SURGE_MULTIPLIER;
        }

        return new FareDetails(baseFare, distanceCost, timeCost, totalFare);
    }
}
