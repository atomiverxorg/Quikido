package com.quikido.auth.repository;

import com.quikido.auth.entity.ActiveRide;
import com.quikido.auth.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, Long> {
    Optional<Ride> findByDriverEmail(String email);

}