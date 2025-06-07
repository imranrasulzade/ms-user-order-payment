package com.example.circuitbreakerpatterntestservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    public String callPaymentApi(Long orderId) {
        String url = "http://localhost:8083/payments/test-get-for-circuit/" + orderId;
        return restTemplate.getForObject(url, String.class);
    }

    public String fallbackPayment(Long orderId, Throwable t) {
        return "Ödəniş servisi hazırda əlçatan deyil. Xahiş edirik sonra yenidən cəhd edin.";
    }
}
