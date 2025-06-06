package com.example.msauthservice.services;

import com.example.msauthservice.dto.UserRoleCacheDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisCacheService {

    private final RedisTemplate<String, UserRoleCacheDto> redisTemplate;

    private static final String USER_ROLE_PREFIX = "user_role:";

    public void saveUserRole(Long userId, UserRoleCacheDto dto) {
        String key = USER_ROLE_PREFIX + userId;
        redisTemplate.opsForValue().set(key, dto, Duration.ofDays(1)); // 1 gunluk TTL
    }

    public Optional<UserRoleCacheDto> getUserRole(Long userId) {
        String key = USER_ROLE_PREFIX + userId;
        Object cached = redisTemplate.opsForValue().get(key);
        if (cached instanceof UserRoleCacheDto dto) {
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    // Client ile etdim ama bunu rabbite deyiserem
    public void evictUserRole(Long userId) {
        String key = USER_ROLE_PREFIX + userId;
        redisTemplate.delete(key);
    }
}
