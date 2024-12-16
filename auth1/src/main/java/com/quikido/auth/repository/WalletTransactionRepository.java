package com.quikido.auth.repository;


import com.quikido.auth.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

    List<WalletTransaction> findByUserId(Long userId);

    List<WalletTransaction> findAllByTransactionTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
