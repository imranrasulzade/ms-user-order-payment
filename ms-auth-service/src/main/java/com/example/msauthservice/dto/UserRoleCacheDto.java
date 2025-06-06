package com.example.msauthservice.dto;

import com.example.msauthservice.enums.Role;
import lombok.Data;

@Data
public class UserRoleCacheDto {
    private Long id;
    private Role role;
}
