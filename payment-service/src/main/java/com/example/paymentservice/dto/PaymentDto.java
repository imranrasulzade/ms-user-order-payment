package com.example.paymentservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private Long orderId;

}
