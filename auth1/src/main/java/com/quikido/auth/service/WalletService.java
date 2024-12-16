package com.quikido.auth.service;

import com.quikido.auth.entity.User;
import com.quikido.auth.entity.Wallet;
import com.quikido.auth.entity.WalletTransaction;
import com.quikido.auth.repository.WalletRepository;
import com.quikido.auth.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletTransactionRepository transactionRepository;

    public Wallet getWallet(User user) {
        return (Wallet) walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public void addFunds(User user, double amount) {
        Wallet wallet = getWallet(user);
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType("CREDIT");
        transaction.setDescription("Wallet top-up");
        transactionRepository.save(transaction);
    }

    public void deductFunds(User user, double amount) {
        Wallet wallet = getWallet(user);
        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType("DEBIT");
        transaction.setDescription("Ride payment");
        transactionRepository.save(transaction);
    }
}
