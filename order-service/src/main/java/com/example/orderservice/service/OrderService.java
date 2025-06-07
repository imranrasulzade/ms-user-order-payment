package com.example.orderservice.service;

import com.example.orderservice.client.PaymentClient;
import com.example.orderservice.client.UserClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.PaymentDto;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentClient paymentClient;
    private final UserClient userClient;

    public OrderDto get(Long id) {
        log.info("Get order start by id {}", id);
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDto orderDto = orderMapper.toDto(orderEntity);
        log.info("Get order end by id {}", id);
        return orderDto;
    }

    public void save(OrderDto orderDto) {
        log.info("Save order start by id {}", orderDto.getId());
        UserDto user;
        try {
            user = userClient.getUserById(orderDto.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            user = null;
        }
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity.setStatus(OrderStatus.CREATED);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        log.info("Save order end by id {}", orderDto.getId());
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(savedOrder.getId());
        paymentDto.setAmount(new BigDecimal("19.99"));
        paymentDto.setCurrency("AZN");
        paymentClient.create(paymentDto);
    }


    public void changeStatus(Long id, Long paymentId, OrderStatus status) {
        log.info("Change order status start by id {}", id);
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderEntity.setPaymentId(paymentId);
        orderEntity.setStatus(status);
        orderRepository.save(orderEntity);
        log.info("Change order status end by id {}", id);

    }

    public Long createBySaga(OrderDto orderDto){
        log.info("Save createBySaga order start by id {}", orderDto.getId());
        UserDto user;
        try {
            user = userClient.getUserById(orderDto.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
            user = null;
        }
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity.setStatus(OrderStatus.CREATED);
        OrderEntity savedOrder = orderRepository.save(orderEntity);
        log.info("Save createBySaga order end by id {}", orderDto.getId());
        return savedOrder.getId();
    }
}
