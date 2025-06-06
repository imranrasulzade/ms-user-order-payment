package com.example.msauthservice.mapper;

import com.example.msauthservice.dto.AuthResponse;
import com.example.msauthservice.entities.AuthUser;
import com.example.msauthservice.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AuthUserMapper {
    public AuthResponse toAuthResponse(AuthUser user, Role role, String token) {
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUserId(user.getUserId());
        response.setRole(role);
        return response;
    }
}
