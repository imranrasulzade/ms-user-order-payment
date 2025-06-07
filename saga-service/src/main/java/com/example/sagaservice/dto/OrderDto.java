package com.example.sagaservice.dto;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    private Integer quantity;
    private Long userId;
    private Long paymentId;
}
