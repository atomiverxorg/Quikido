package com.quikido.auth.repository;

import com.quikido.auth.entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}