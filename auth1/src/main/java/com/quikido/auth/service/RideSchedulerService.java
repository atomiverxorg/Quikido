package com.quikido.auth.service;

import com.quikido.auth.dto.RouteDetails;
import com.quikido.auth.entity.ActiveRide;
import com.quikido.auth.entity.ScheduledRide;
import com.quikido.auth.repository.ActiveRideRepository;
import com.quikido.auth.repository.ScheduledRideRepository;
import com.quikido.auth.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideSchedulerService {
    @Autowired
    private ScheduledRideRepository scheduledRideRepository;

    @Autowired
    private DriverMatchingService driverMatchingService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ActiveRideRepository rideRepository;

    @Autowired
    private RouteService routeService;

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void allocateScheduledRides() {
        List<ScheduledRide> pendingRides = scheduledRideRepository.findByScheduledTimeBeforeAndIsCompleted(
            LocalDateTime.now(), false
        );

        for (ScheduledRide ride : pendingRides) {
            Driver driver = driverMatchingService.findNearestDriver(ride.getPickupLatitude(), ride.getPickupLongitude());

            if (driver != null) {
                ride.setDriverEmail(driver.getEmail());
                ride.setCompleted(true);

                scheduledRideRepository.save(ride);

                // Notify driver and passenger
                notificationService.notifyDriver(driver.getEmail(), "New scheduled ride assigned");
                notificationService.notifyPassenger(ride.getPassengerEmail(), "Your scheduled ride is on its way");
            }
        }
    }
    @Scheduled(fixedRate = 300000) // Check every 5 minutes
    public void updateRoutesForActiveRides() {
        List<ActiveRide> activeRides = rideRepository.findActiveRides();

        for (ActiveRide ride : activeRides) {
            RouteDetails newRoute = routeService.getOptimizedRoute(
                    ride.getCurrentLatitude(),
                    ride.getCurrentLongitude(),
                    ride.getDropLatitude(),
                    ride.getDropLongitude()
            );

            // Update the driver app with new route data
            notificationService.notifyDriver(
                    ride.getDriverEmail(),
                    "Route updated: " + newRoute.getDuration()
            );
        }
    }


}
