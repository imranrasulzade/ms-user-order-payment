package com.example.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8084/api/auth")
public interface AuthClient {
    @DeleteMapping("/user/role-cache-evict/{id}")
    void evictRoleCache(@PathVariable Long id);

}
