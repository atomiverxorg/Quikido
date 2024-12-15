package com.quikido.auth.repository;

import com.quikido.auth.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
//    Optional<Driver> findById(String email);

    List<Driver> findAllByIsAvailable(boolean b);
}