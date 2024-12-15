package com.quikido.auth.controller;


import com.quikido.auth.model.Role;
import com.quikido.auth.utils.RoleUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import com.quikido.auth.entity.User;
import com.quikido.auth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    private RoleUtil roleUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String password = request.get("password");
        String name = request.get("name");
        Role role = Role.valueOf(request.get("role").toUpperCase());

        userService.registerUser(name, email, password, role);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String token = userService.authenticateUser(email, password);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/driver/details")
    public ResponseEntity<String> getDriverDetails(HttpServletRequest request) {

        if (!roleUtil.hasRole(request, Role.DRIVER)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        return ResponseEntity.ok("Driver details accessed successfully");
    }

    @GetMapping("/admin/reports")
    public ResponseEntity<String> getAdminReports(HttpServletRequest request) {
        if (!roleUtil.hasRole(request, Role.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
        return ResponseEntity.ok("Admin reports accessed successfully");
    }
}
