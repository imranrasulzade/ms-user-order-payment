package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderDto get(Long id) {
        log.info("Get order start by id {}", id);
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        OrderDto orderDto = orderMapper.toDto(orderEntity);
        log.info("Get order end by id {}", id);
        return orderDto;
    }
}
