package com.example.msauthservice.dto;

import com.example.msauthservice.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private Long userId;
    private Role role;
}
