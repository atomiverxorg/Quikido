package com.quikido.auth.controller;

import com.quikido.auth.entity.Payment;
import com.quikido.auth.repository.PaymentRepository;
import com.quikido.auth.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/create-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestParam double amount, @RequestParam String currency) {
        try {
            PaymentIntent paymentIntent = paymentService.createPaymentIntent(amount, currency, "Taxi Fare");
            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/transactions")
    public List<Payment> getAllTransactions() {
        return paymentRepository.findAll();
    }

}
