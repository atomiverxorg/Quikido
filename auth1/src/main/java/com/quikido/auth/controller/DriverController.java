package com.quikido.auth.controller;

import com.quikido.auth.security.JwtUtil;
import com.quikido.auth.service.DriverService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    DriverService driverService;

    @PostMapping("/driver/location")
    public ResponseEntity<String> updateDriverLocation(@RequestParam double lat, @RequestParam double lon, HttpServletRequest request) {

        String driverEmail = jwtUtil.extractEmail(request.getHeader("Authorization"));

        driverService.updateLocation(driverEmail, lat, lon);
        return ResponseEntity.ok("Location updated successfully");
    }

}
