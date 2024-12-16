package com.quikido.auth.controller;

import com.quikido.auth.entity.Promotion;
import com.quikido.auth.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/promotions")
public class AdminPromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        promotion.setActive(true);
        promotionRepository.save(promotion);
        return ResponseEntity.ok("Promotion created successfully.");
    }

    @PostMapping("/{id}/toggle")
    public ResponseEntity<?> togglePromotion(@PathVariable Long id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
        promotion.setActive(!promotion.isActive());
        promotionRepository.save(promotion);
        return ResponseEntity.ok("Promotion status updated.");
    }
}
