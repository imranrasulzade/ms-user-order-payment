package com.example.sagaservice.client;

import com.example.sagaservice.dto.PaymentDto;
import com.example.sagaservice.enums.PaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service", url = "http://localhost:8083/payments")
public interface PaymentClient {

    @PostMapping("/create-by-saga")
    Long createBySaga(@RequestBody PaymentDto paymentDto);

    @PostMapping("/status")
    void updateStatus(@RequestParam Long id, @RequestParam PaymentStatus status);



}
