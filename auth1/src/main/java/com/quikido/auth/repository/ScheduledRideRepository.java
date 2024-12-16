package com.quikido.auth.repository;

import com.quikido.auth.entity.ScheduledRide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ScheduledRideRepository extends JpaRepository<ScheduledRide, Long> {
    List<ScheduledRide> findByScheduledTimeBeforeAndIsCompleted(LocalDateTime now, boolean b);
}
