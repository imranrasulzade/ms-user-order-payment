package com.example.msauthservice.dto;

import com.example.msauthservice.enums.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleCacheDto implements Serializable {
    private Long id;
    private Role role;
}
