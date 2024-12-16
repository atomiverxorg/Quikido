package com.quikido.auth.controller;

import com.quikido.auth.entity.Promotion;
import com.quikido.auth.repository.PromotionRepository;
import com.quikido.auth.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api//promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private PromotionRepository promotionRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        promotionService.createPromotion(promotion);
        return ResponseEntity.ok("Promotion created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validatePromoCode(@RequestParam String promoCode) {
        Promotion promotion = promotionRepository.findByCodeAndIsActiveTrue(promoCode)
                .orElseThrow(() -> new RuntimeException("Invalid or expired promo code"));

        return ResponseEntity.ok(promotion);
    }

}
