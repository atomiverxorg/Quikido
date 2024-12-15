package com.quikido.auth.controller;

import com.quikido.auth.dto.RideRequestDTO;
import com.quikido.auth.entity.Driver;
import com.quikido.auth.entity.RideRequest;
import com.quikido.auth.model.RideRequestStatus;
import com.quikido.auth.repository.RideRequestRepository;
import com.quikido.auth.security.JwtUtil;
import com.quikido.auth.service.DriverMatchingService;
import com.quikido.auth.service.DriverService;
import com.quikido.auth.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class RideController {

    @Autowired
    DriverMatchingService driverMatchingService;

    @Autowired
    RideRequestRepository rideRequestRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    NotificationService notificationService;

    @Autowired
    DriverService driverService;

    @PostMapping("/rides/request")
    public ResponseEntity<String> requestRide(@RequestBody RideRequestDTO request) {

        Driver nearestDriver = driverMatchingService.findNearestDriver(request.getPickupLatitude(), request.getPickupLongitude());

        if (nearestDriver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No drivers available");
        }

        // Create a new ride request
        RideRequest rideRequest = new RideRequest();
        rideRequest.setPassengerEmail(request.getPassengerEmail());
        rideRequest.setDriverEmail(nearestDriver.getEmail());
        rideRequest.setStatus(RideRequestStatus.PENDING);
        rideRequest.setRequestTime(LocalDateTime.now());
        rideRequestRepository.save(rideRequest);

        // Notify the driver (e.g., via push notification)
        notificationService.notifyDriver(nearestDriver.getEmail(), "New ride request");

        return ResponseEntity.ok("Ride request sent to the driver");
    }

    @PostMapping("/rides/respond")
    public ResponseEntity<String> respondToRide(@RequestParam Long requestId, @RequestParam boolean accept, HttpServletRequest request) {
        String driverEmail = jwtUtil.extractEmail(request.getHeader("Authorization"));

        RideRequest rideRequest = rideRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Ride request not found"));

        if (!rideRequest.getDriverEmail().equals(driverEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized action");
        }

        if (accept) {
            rideRequest.setStatus(RideRequestStatus.ACCEPTED);
                driverService.markAsUnavailable(driverEmail);
            return ResponseEntity.ok("Ride accepted");
        } else {
            rideRequest.setStatus(RideRequestStatus.REJECTED);
            return ResponseEntity.ok("Ride rejected");
        }
    }
}
