package com.quikido.auth.service;

import com.quikido.auth.entity.Promotion;
import com.quikido.auth.entity.Referral;
import com.quikido.auth.entity.User;
import com.quikido.auth.repository.PromotionRepository;
import com.quikido.auth.repository.ReferralRepository;
import com.quikido.auth.repository.UserRepository;
import com.quikido.auth.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;


    public void createPromotion(Promotion promotion) {}

    public Object getAllPromotions() {
        return promotionRepository.findAll();
    }
}
