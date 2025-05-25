package com.example.paymentservice.client;

import com.example.paymentservice.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service", url = "http://localhost:8082/orders")
public interface OrderClient {
    @GetMapping("/{id}")
    OrderDto getOrderById(@PathVariable Long id);
}
