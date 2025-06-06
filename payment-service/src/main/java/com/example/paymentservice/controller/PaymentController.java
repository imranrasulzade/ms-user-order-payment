package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public void create(@RequestBody PaymentDto paymentDto) {
        paymentService.create(paymentDto);
    }
}
