package com.quikido.auth.controller;

import com.quikido.auth.dto.FareDetails;
import com.quikido.auth.dto.RouteDetails;
import com.quikido.auth.entity.RideCompletionDetails;
import com.quikido.auth.service.FareCalculationService;
import com.quikido.auth.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fare")
public class FareController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private FareCalculationService fareCalculationService;

    @GetMapping("/estimate")
    public FareDetails estimateFare(
            @RequestParam double pickupLat,
            @RequestParam double pickupLng,
            @RequestParam double dropLat,
            @RequestParam double dropLng,
            @RequestParam boolean isSurge) {

        RouteDetails route = routeService.getOptimizedRoute(pickupLat, pickupLng, dropLat, dropLng);
        double distanceKm = Double.parseDouble(route.getDistance().replace(" km", ""));
        double durationMinutes = Double.parseDouble(route.getDuration().replace(" mins", ""));

        return fareCalculationService.calculateFare(distanceKm, durationMinutes, isSurge);
    }
    @PostMapping("/complete")
    public FareDetails completeRide(@RequestBody RideCompletionDetails details) {
        return fareCalculationService.calculateFare(
                details.getActualDistance(),
                details.getActualDuration(),
                details.isSurge()
        );
    }

}
