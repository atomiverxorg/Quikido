package com.quikido.auth.repository;

import com.quikido.auth.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
//    Locale findByCodeAndIsActiveTrue(String promoCode);

    Optional<Promotion> findByCodeAndIsActiveTrue(String code);
}
