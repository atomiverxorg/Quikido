package com.quikido.auth.controller;

import com.quikido.auth.dto.RideRequestDTO;
import com.quikido.auth.dto.ScheduledRideDTO;
import com.quikido.auth.entity.Driver;
import com.quikido.auth.entity.Ride;
import com.quikido.auth.entity.RideRequest;
import com.quikido.auth.model.RideRequestStatus;
import com.quikido.auth.entity.ScheduledRide;
import com.quikido.auth.model.RideStatus;
import com.quikido.auth.repository.RideRepository;
import com.quikido.auth.repository.RideRequestRepository;
import com.quikido.auth.repository.ScheduledRideRepository;
import com.quikido.auth.security.JwtUtil;
import com.quikido.auth.service.DriverMatchingService;
import com.quikido.auth.service.DriverService;
import com.quikido.auth.service.NotificationService;
import com.quikido.auth.service.WalletService;
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
    ScheduledRideRepository scheduledRideRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    NotificationService notificationService;

    @Autowired
    DriverService driverService;

    @Autowired
    RideRepository rideRepository;

    @Autowired
    private WalletService walletService;

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
    @PostMapping("/rides/schedule")
    public ResponseEntity<String> scheduleRide(@RequestBody ScheduledRide rideRequest) {
        if (rideRequest.getScheduledTime().isBefore(LocalDateTime.now().plusMinutes(15))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Scheduled time must be at least 15 minutes in the future");
        }

        ScheduledRide scheduledRide = new ScheduledRide();
        scheduledRide.setPassengerEmail(rideRequest.getPassengerEmail());
        scheduledRide.setPickupLatitude(rideRequest.getPickupLatitude());
        scheduledRide.setPickupLongitude(rideRequest.getPickupLongitude());
        scheduledRide.setScheduledTime(rideRequest.getScheduledTime());
        scheduledRide.setDriverEmail(rideRequest.getDriverEmail());

        scheduledRideRepository.save(scheduledRide);
        return ResponseEntity.ok("Ride scheduled successfully");
    }

    @PostMapping("/{rideId}/cancel")
    public ResponseEntity<?> cancelRide(@PathVariable Long rideId, @RequestParam boolean isUserInitiated) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!ride.isCancelable()) {
            return ResponseEntity.badRequest().body("Ride cannot be canceled.");
        }

        double refundAmount = calculateRefundAmount(ride, isUserInitiated);
        walletService.processRefund(ride.getPassenger().getUser(), refundAmount, "Ride #" + rideId);

        ride.setStatus(RideStatus.CANCELED);
        rideRepository.save(ride);

        return ResponseEntity.ok("Ride canceled and refund processed.");
    }

    private double calculateRefundAmount(Ride ride, boolean isUserInitiated) {
        if (isUserInitiated && ride.getStartTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
            return ride.getFare() * 0.50; // 50% refund for late cancellations
        }
        return ride.getFare(); // Full refund otherwise
    }
}
