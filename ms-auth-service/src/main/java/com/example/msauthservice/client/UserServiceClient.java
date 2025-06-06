package com.example.msauthservice.client;


import com.example.msauthservice.dto.UserDto;
import com.example.msauthservice.dto.UserRoleCacheDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserServiceClient {

    @GetMapping("/users/role/{id}")
    UserRoleCacheDto getUserRole(@PathVariable("id") Long userId);

    @PostMapping("/users")
    Long addUser(@RequestBody UserDto userDto);
}
