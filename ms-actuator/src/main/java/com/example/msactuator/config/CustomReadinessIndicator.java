package com.example.msactuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("readinessStateHealthIndicator")
public class CustomReadinessIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.down().withDetail("readiness", "Dependency not ready").build();
    }
}
