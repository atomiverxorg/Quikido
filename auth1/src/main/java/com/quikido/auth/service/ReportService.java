package com.quikido.auth.service;

import com.quikido.auth.entity.WalletTransaction;
import com.quikido.auth.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private WalletTransactionRepository transactionRepository;

    public List<WalletTransaction> getTransactionReport(LocalDateTime startDate, LocalDateTime endDate) {
        return transactionRepository.findAllByTransactionTimeBetween(startDate, endDate);
    }
}
