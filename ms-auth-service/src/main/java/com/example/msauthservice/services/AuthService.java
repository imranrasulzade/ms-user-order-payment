package com.example.msauthservice.services;

import com.example.msauthservice.client.UserServiceClient;
import com.example.msauthservice.dto.AuthResponse;
import com.example.msauthservice.dto.LoginRequest;
import com.example.msauthservice.dto.RegisterRequest;
import com.example.msauthservice.dto.UserRoleCacheDto;
import com.example.msauthservice.entities.AuthUser;
import com.example.msauthservice.enums.AuthStatus;
import com.example.msauthservice.enums.Role;
import com.example.msauthservice.mapper.AuthUserMapper;
import com.example.msauthservice.repositories.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtUtil jwtUtil;
    private final RedisCacheService redisCacheService;
    private final AuthUserMapper mapper;
    private final UserServiceClient userClient;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        AuthUser user = authUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role userRole = getRoleForUser(user.getUserId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash()))
            throw new RuntimeException("Invalid credentials");

        if (user.getStatus() != AuthStatus.ACTIVE)
            throw new RuntimeException("User is not active");

        String token = jwtUtil.generateToken(user);
        return mapper.toAuthResponse(user, userRole, token);
    }

    public AuthResponse register(RegisterRequest request) {
        if (authUserRepository.findByUsername(request.getUsername()).isPresent())
            throw new RuntimeException("Username already taken");

        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        // TODO user client ile user post qayidan id set edirem alta
        user.setStatus(AuthStatus.ACTIVE);
        user.setUserId(1L); // TODO deyish

        AuthUser saved = authUserRepository.save(user);
        Role userRole = getRoleForUser(saved.getUserId());
        String token = jwtUtil.generateToken(saved);
        return mapper.toAuthResponse(saved, userRole, token);
    }

    public Role getRoleForUser(Long userId) {
        // Əvvəlcə cache-dən yoxla
        return redisCacheService.getUserRole(userId)
                .map(UserRoleCacheDto::getRole)
                .orElseGet(() -> {
                    // Əgər cache-də yoxdursa user-service-ə sorğu at
                    UserRoleCacheDto dto = userClient.getUserRole(userId);
                    redisCacheService.saveUserRole(userId, dto);
                    return dto.getRole();
                });
    }

}
