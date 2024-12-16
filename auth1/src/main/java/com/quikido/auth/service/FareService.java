package com.quikido.auth.service;

import com.quikido.auth.entity.Promotion;
import com.quikido.auth.entity.User;
import com.quikido.auth.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FareService {

    @Autowired
    private PromotionRepository promotionRepository;

    public double calculateDiscountedFare(double originalFare, String promoCode, User user) {
        if (promoCode == null || promoCode.isEmpty()) return originalFare;

        Promotion promotion = promotionRepository.findByCodeAndIsActiveTrue(promoCode)
                .orElseThrow(() -> new RuntimeException("Invalid or expired promo code"));

        // Check eligibility
        if (!promotion.getEligibleUsers().isEmpty() && !promotion.getEligibleUsers().contains(user)) {
            throw new RuntimeException("You are not eligible for this promo code");
        }

        // Calculate discount
        double discount = (originalFare * promotion.getDiscountPercentage()) / 100;
        discount = Math.min(discount, promotion.getMaxDiscountAmount());

        return originalFare - discount;
    }
}
