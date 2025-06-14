package com.example.orderservice.client;

import com.example.orderservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable Long id);
}
