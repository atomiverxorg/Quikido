package com.quikido.auth.repository;


import com.quikido.auth.entity.User;
import com.quikido.auth.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    <T> ScopedValue<T> findByUser(User user);
}
