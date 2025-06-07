package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto get(@PathVariable Long id) {
        return orderService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }

    @PostMapping("/status")
    public void updateStatus(@RequestParam Long id,
                             @RequestParam Long paymentId,
                             @RequestParam OrderStatus status) {
        orderService.changeStatus(id, paymentId, status);
    }

    @PostMapping("/create-by-saga")
    public Long createBySaga(@RequestBody OrderDto orderDto) {
        return orderService.createBySaga(orderDto);
    }

}
