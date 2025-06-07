package com.example.sagaservice.controller;

import com.example.sagaservice.dto.OrderDto;
import com.example.sagaservice.service.SagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/saga")
public class SagaController {

    private final SagaService sagaService;

    @PostMapping("/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public void orderPayment(@RequestBody OrderDto orderDto) {
        sagaService.handleOrderPaymentSaga(orderDto);
    }
}
