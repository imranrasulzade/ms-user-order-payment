package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public void create(@RequestBody PaymentDto paymentDto) {
        paymentService.create(paymentDto);
    }


    @PostMapping("/create-by-saga")
    public Long createBySaga(@RequestBody PaymentDto paymentDto) {
        return paymentService.createBySaga(paymentDto);
    }


    @PostMapping("/status")
    public void changeStatus(@RequestParam Long id, @RequestParam PaymentStatus status) {
        paymentService.changeStatus(id, status);
    }
}
