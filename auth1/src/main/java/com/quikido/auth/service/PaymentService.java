package com.quikido.auth.service;

import com.quikido.auth.entity.Payment;
import com.quikido.auth.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentIntent createPaymentIntent(double amount, String currency, String description) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (amount * 100)); // Amount in smallest currency unit
        params.put("currency", currency);
        params.put("description", description);
        params.put("payment_method_types", List.of("card"));

        return PaymentIntent.create(params);
    }

    public void savePayment(PaymentIntent paymentIntent) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentIntent.getId());
        payment.setAmount(paymentIntent.getAmount() / 100.0);
        payment.setCurrency(paymentIntent.getCurrency());
        payment.setStatus(paymentIntent.getStatus());
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);
    }

}
