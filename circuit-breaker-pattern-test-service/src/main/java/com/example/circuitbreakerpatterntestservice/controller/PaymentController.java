package com.example.circuitbreakerpatterntestservice.controller;

import com.example.circuitbreakerpatterntestservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/circuit/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/call-payment/{id}")
    public String callPayment(@PathVariable Long id) {
        return paymentService.callPaymentApi(id);
    }
}
