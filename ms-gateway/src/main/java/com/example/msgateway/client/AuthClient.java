package com.example.msgateway.client;


import com.example.msgateway.enums.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8084/api/auth")
public interface AuthClient {

    @GetMapping("/user/role/{id}")
    Role getUserRole(@PathVariable("id") Long userId);
}
