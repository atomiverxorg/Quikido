package com.quikido.auth.controller;



import com.quikido.auth.entity.Ride;
import com.quikido.auth.entity.User;
import com.quikido.auth.model.Role;
import com.quikido.auth.service.AdminService;
import com.quikido.auth.utils.RoleUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleUtil roleUtil;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        if (roleUtil.hasRole(request, Role.ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PutMapping("/users/{id}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam boolean status) {
        adminService.updateUserStatus(id, status);
        return ResponseEntity.ok("User status updated successfully");
    }

    @GetMapping("/rides")
    public ResponseEntity<List<Ride>> getAllRides() {
        return ResponseEntity.ok(adminService.getAllRides());
    }

    @GetMapping("/reports/daily-revenue")
    public ResponseEntity<Double> getDailyRevenue() {
        return ResponseEntity.ok(adminService.calculateDailyRevenue());
    }
}
