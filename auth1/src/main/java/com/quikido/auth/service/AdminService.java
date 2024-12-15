package com.quikido.auth.service;



import com.quikido.auth.entity.Ride;
import com.quikido.auth.entity.User;
import com.quikido.auth.model.RideStatus;
import com.quikido.auth.repository.RideRepository;
import com.quikido.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserStatus(Long userId, boolean status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(status);
        userRepository.save(user);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public double calculateDailyRevenue() {
        return rideRepository.findAll().stream()
                .filter(ride -> ride.getStatus() == RideStatus.COMPLETED)
                .mapToDouble(Ride::getFare)
                .sum();
    }
}
