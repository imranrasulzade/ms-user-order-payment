package com.example.msauthservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "MS - Auth Service API",
                version = "1.0",
                description = "Auth service endpoints"
        )
)
public class MsAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAuthServiceApplication.class, args);
    }

}
