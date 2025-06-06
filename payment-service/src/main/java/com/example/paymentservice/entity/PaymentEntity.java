package com.example.paymentservice.entity;

import com.example.paymentservice.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "t_payments")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String currency;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
