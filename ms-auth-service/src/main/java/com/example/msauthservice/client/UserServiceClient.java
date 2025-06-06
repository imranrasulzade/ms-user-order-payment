package com.example.msauthservice.client;

import com.example.msauthservice.dto.UserRoleCacheDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8083")
public interface UserServiceClient {

    @GetMapping("/api/users/{userId}/role")
    UserRoleCacheDto getUserRole(@PathVariable("userId") Long userId);
}
