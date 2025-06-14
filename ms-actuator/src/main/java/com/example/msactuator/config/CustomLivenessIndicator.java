package com.example.msactuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("livenessStateHealthIndicator")
public class CustomLivenessIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().withDetail("liveness", "I'm alive").build();
    }
}
