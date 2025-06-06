package com.example.orderservice.client;

import com.example.orderservice.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-client", url = "http://localhost:8083/payments")
public interface PaymentClient {

    @PostMapping("/create")
    void create(@RequestBody PaymentDto paymentDto);
}
