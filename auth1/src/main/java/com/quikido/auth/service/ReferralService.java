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
public class ReferralService {

    @Autowired
    private ReferralRepository referralRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    public String generateReferralCode(User user) {
        String referralCode = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        user.setReferralCode(referralCode);
        userRepository.save(user);
        return referralCode;
    }

    public void rewardReferral(String referralCode, User newUser) {
        User referrer = (User) userRepository.findByReferralCode(referralCode)
                .orElseThrow(() -> new RuntimeException("Invalid referral code"));

        Referral referral = new Referral();
        referral.setReferrer(referrer);
        referral.setReferee(newUser);
        referral.setReferralCode(referralCode);
        referral.setRewardAmount(10.0); // Example reward
        referral.setRewardClaimed(false);
        referralRepository.save(referral);

        referrer.getWallet().setBalance(referrer.getWallet().getBalance() + 10.0);
        walletRepository.save(referrer.getWallet());
    }

    public Promotion validatePromoCode(String promoCode) {
        return promotionRepository.findByCodeAndIsActiveTrue(promoCode)
                .filter(promo -> promo.getValidFrom().isBefore(LocalDateTime.now()) &&
                        promo.getValidTo().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Invalid or expired promo code"));
    }


}
