package com.example.msgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("auth-service", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8084"))

                .route("user-service", r -> r.path("/users/**")
                        .uri("http://localhost:8081"))

                .route("order-service", r -> r.path("/orders/**")
                        .uri("http://localhost:8082"))

                .route("payment-service", r -> r.path("/payments/**")
                        .uri("http://localhost:8083"))

                .build();
    }
}
