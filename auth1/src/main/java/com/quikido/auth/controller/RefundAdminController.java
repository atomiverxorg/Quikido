package com.quikido.auth.controller;

import com.quikido.auth.dto.RefundRequest;
import com.quikido.auth.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/refunds")
public class RefundAdminController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/issue")
    public ResponseEntity<?> issueRefund(@RequestBody RefundRequest refundRequest) {
        walletService.processRefund(refundRequest.getUserId(), refundRequest.getAmount(), refundRequest.getReason());
        return ResponseEntity.ok("Refund processed successfully.");
    }
}
