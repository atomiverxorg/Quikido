package com.quikido.auth.controller;

import com.quikido.auth.entity.Promotion;
import com.quikido.auth.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/create")
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        promotionService.createPromotion(promotion);
        return ResponseEntity.ok("Promotion created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }
}
