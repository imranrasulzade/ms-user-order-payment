package com.example.msgateway.dto;

import com.example.msgateway.enums.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleCacheDto implements Serializable {
    private Long id;
    private Role role;
}
