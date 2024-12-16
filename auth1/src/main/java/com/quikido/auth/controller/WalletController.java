package com.quikido.auth.controller;

import com.quikido.auth.dto.PaymentCallback;
import com.quikido.auth.entity.User;
import com.quikido.auth.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<?> getWallet(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(walletService.getWallet(user));
    }

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@AuthenticationPrincipal User user, @RequestParam double amount) {
        walletService.addFunds(user, amount);
        return ResponseEntity.ok("Funds added successfully");
    }

    @PostMapping("/deduct-funds")
    public ResponseEntity<?> deductFunds(@AuthenticationPrincipal User user, @RequestParam double amount) {
        walletService.deductFunds(user, amount);
        return ResponseEntity.ok("Funds deducted successfully");
    }

    @PostMapping("/payment-callback")
    public ResponseEntity<?> handlePaymentCallback(@RequestBody PaymentCallback payload) {
        // Verify and process payment
        walletService.addFunds(payload.getUser(), payload.getAmount());
        return ResponseEntity.ok("Payment successful");
    }

}
