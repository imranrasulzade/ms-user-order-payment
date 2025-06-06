package com.example.msgateway.dto;

import com.example.msgateway.enums.Role;
import lombok.Data;

@Data
public class UserRoleCacheDto {
    private Long id;
    private Role role;
}
