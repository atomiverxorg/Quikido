package com.quikido.auth.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User referrer; // User who referred

    @OneToOne
    private User referee; // User who signed up using the code

    private String referralCode;

    private double rewardAmount; // Credit for the referrer

    private boolean rewardClaimed; // Status of reward

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Getters and setters


    public User getReferrer() {
        return referrer;
    }

    public User getReferee() {
        return referee;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public double getRewardAmount() {
        return rewardAmount;
    }

    public boolean isRewardClaimed() {
        return rewardClaimed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setReferrer(User referrer) {
        this.referrer = referrer;
    }

    public void setReferee(User referee) {
        this.referee = referee;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public void setRewardAmount(double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public void setRewardClaimed(boolean rewardClaimed) {
        this.rewardClaimed = rewardClaimed;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
