package com.example.orderservice.entity;

import com.example.orderservice.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private Integer quantity;
    private Long userId;
    private Long paymentId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
