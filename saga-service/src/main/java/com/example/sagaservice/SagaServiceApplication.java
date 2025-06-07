package com.example.sagaservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Saga Service API",
                version = "1.0",
                description = "Saga service endpoints"
        )
)
public class SagaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaServiceApplication.class, args);
    }

}
