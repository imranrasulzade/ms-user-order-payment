package com.example.paymentservice.service;

import com.example.paymentservice.client.OrderClient;
import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.entity.PaymentEntity;
import com.example.paymentservice.enums.OrderStatus;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.mapper.PaymentMapper;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderClient orderClient;

    public void create(PaymentDto paymentDto) {
        try {
            PaymentEntity paymentEntity = paymentMapper.toEntity(paymentDto);
            paymentEntity.setStatus(PaymentStatus.CREATED);
            Thread.sleep(1000);
            paymentEntity.setStatus(PaymentStatus.SUCCESS);
            PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);
            orderClient.updateOrderStatus(savedPaymentEntity.getOrderId(), savedPaymentEntity.getId(), OrderStatus.CONFIRMED);

        } catch (Exception e) {
            e.printStackTrace();
            orderClient.updateOrderStatus(paymentDto.getId(), null, OrderStatus.REJECTED);
        }
    }


}
