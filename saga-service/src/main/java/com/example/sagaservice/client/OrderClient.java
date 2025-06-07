package com.example.sagaservice.client;

import com.example.sagaservice.dto.OrderDto;
import com.example.sagaservice.enums.OrderStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "http://localhost:8082/orders")
public interface OrderClient {

    @PostMapping("/status")
    void updateStatus(@RequestParam Long id,
                      @RequestParam Long paymentId,
                      @RequestParam OrderStatus status);

    @PostMapping("/create-by-saga")
    Long createBySaga(@RequestBody OrderDto orderDto);
}
