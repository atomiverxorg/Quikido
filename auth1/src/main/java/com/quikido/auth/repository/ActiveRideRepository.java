package com.quikido.auth.repository;

import com.quikido.auth.entity.ActiveRide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActiveRideRepository extends JpaRepository<ActiveRide, Long> {
    List<ActiveRide> findActiveRides();
}
