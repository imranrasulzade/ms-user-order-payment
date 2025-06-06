package com.example.msgateway.service;

import com.example.msgateway.client.AuthClient;
import com.example.msgateway.dto.UserRoleCacheDto;
import com.example.msgateway.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final RedisCacheService redisCacheService;
    private final AuthClient authClient;

    public Role getUserRole(Long userId) {
        Optional<UserRoleCacheDto> userRoleCacheDto = redisCacheService.getUserRole(userId);
        if (userRoleCacheDto.isPresent()) {
            return userRoleCacheDto.get().getRole();
        }
        log.info("user role fetching from authClient by userId: {}", userId);
        return authClient.getUserRole(userId);
    }

}
