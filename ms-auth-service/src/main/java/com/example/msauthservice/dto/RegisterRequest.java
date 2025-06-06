package com.example.msauthservice.dto;

import com.example.msauthservice.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
