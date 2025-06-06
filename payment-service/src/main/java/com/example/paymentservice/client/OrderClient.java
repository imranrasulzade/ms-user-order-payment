package com.example.paymentservice.client;

import com.example.paymentservice.dto.OrderDto;
import com.example.paymentservice.enums.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "order-service", url = "http://localhost:8082/orders")
public interface OrderClient {
    @GetMapping("/{id}")
    OrderDto getOrderById(@PathVariable Long id);

    @PostMapping("/status")
    void updateOrderStatus(@RequestParam Long id, @RequestParam Long paymentId, @RequestParam OrderStatus status);
}
