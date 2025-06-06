package com.example.userservice.dto;

import com.example.userservice.enums.Role;
import lombok.Data;

@Data
public class UserRoleCacheDto {
    private Long id;
    private Role role;
}