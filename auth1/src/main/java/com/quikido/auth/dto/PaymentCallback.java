package com.quikido.auth.dto;

import com.quikido.auth.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentCallback {
    private User user;
    private double amount;

    public PaymentCallback(User user, String amount) {
        this.user = user;
        this.amount = Double.parseDouble(amount);
    }
    // Getters and Setters
}
