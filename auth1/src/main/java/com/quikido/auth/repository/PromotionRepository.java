package com.quikido.auth.repository;

import com.quikido.auth.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Locale findByCodeAndIsActiveTrue(String promoCode);
}
