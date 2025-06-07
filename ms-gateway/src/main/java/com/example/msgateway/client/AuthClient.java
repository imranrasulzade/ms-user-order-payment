package com.example.msgateway.client;


import com.example.msgateway.enums.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8084/api/auth")
public interface AuthClient {

    @GetMapping("/user/role/{id}")
    Role getUserRole(@PathVariable("id") Long userId);
}


//@FeignClient(name = "auth-service")
//public interface AuthClient {
//
//    @GetMapping("/api/auth/user/role/{id}")
//    Role getUserRole(@PathVariable("id") Long userId);
//}

