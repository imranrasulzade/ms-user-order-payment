package com.example.orderservice.dto;

import com.example.orderservice.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    private Integer quantity;
    private Long userId;
    private Long paymentId;
    private OrderStatus status;
}
