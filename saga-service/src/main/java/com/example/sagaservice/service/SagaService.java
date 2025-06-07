package com.example.sagaservice.service;

import com.example.sagaservice.client.OrderClient;
import com.example.sagaservice.client.PaymentClient;
import com.example.sagaservice.dto.OrderDto;
import com.example.sagaservice.dto.PaymentDto;
import com.example.sagaservice.entity.SagaRequestEntity;
import com.example.sagaservice.enums.OrderStatus;
import com.example.sagaservice.enums.PaymentStatus;
import com.example.sagaservice.enums.Status;
import com.example.sagaservice.repository.SagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SagaService {

    private final SagaRepository sagaRepository;
    private final OrderClient orderClient;
    private final PaymentClient paymentClient;

    public void handleOrderPaymentSaga(OrderDto orderRequest) {
        UUID sagaId = UUID.randomUUID();
        SagaRequestEntity saga = new SagaRequestEntity();
        saga.setSagaId(sagaId);
        saga.setStatus(Status.PENDING);
        sagaRepository.save(saga);

        try {
            Long orderId = orderClient.createBySaga(orderRequest);
            saga.setOrderId(orderId);
            saga.setStatus(Status.ORDER_CREATED);
            sagaRepository.save(saga);

            PaymentDto paymentDto = PaymentDto.builder()
                    .orderId(orderId)
                    .amount(new BigDecimal("20.00"))
                    .currency("AZN")
                    .build();
            Long paymentId = paymentClient.createBySaga(paymentDto);
            saga.setStatus(Status.PAYMENT_DONE);
            saga.setPaymentId(paymentId);
            sagaRepository.save(saga);

            orderClient.updateStatus(orderId, paymentId, OrderStatus.CONFIRMED);
            saga.setStatus(Status.COMPLETED);
            sagaRepository.save(saga);
        } catch (Exception ex) {
            saga.setStatus(Status.FAILED);
            saga.setErrorMessage(ex.getMessage());
            sagaRepository.save(saga);

            if (saga.getOrderId() != null)
                orderClient.updateStatus(saga.getOrderId(), null, OrderStatus.REJECTED);

            if (saga.getPaymentId() != null)
                paymentClient.updateStatus(saga.getPaymentId(), PaymentStatus.FAILED);
        }
    }


}
