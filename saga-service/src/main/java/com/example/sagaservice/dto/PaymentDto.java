package com.example.sagaservice.dto;

import com.example.sagaservice.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private String currency;
    private Long orderId;
    private PaymentStatus status;

}
