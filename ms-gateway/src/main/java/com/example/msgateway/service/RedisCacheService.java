package com.example.msgateway.service;

import com.example.msgateway.dto.UserRoleCacheDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCacheService {

    private final RedisTemplate<String, UserRoleCacheDto> redisTemplate;

    private static final String USER_ROLE_PREFIX = "user_role:";

    public Optional<UserRoleCacheDto> getUserRole(Long userId) {
        String key = USER_ROLE_PREFIX + userId;
        Object cached = redisTemplate.opsForValue().get(key);
        if (cached instanceof UserRoleCacheDto dto) {
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
