package com.quikido.auth.service;

import com.quikido.auth.entity.Driver;
import com.quikido.auth.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver findNearestDriver(double passengerLat, double passengerLong) {
        List<Driver> availableDrivers = driverRepository.findAllByIsAvailable(true);

        Driver nearestDriver = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Driver driver : availableDrivers) {
            double distance = calculateDistance(
                passengerLat,
                passengerLong,
                driver.getCurrentLatitude(),
                driver.getCurrentLongitude()
            );

            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestDriver = driver;
            }
        }

        return nearestDriver;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }
}
