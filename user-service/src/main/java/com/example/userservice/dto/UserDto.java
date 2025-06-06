package com.example.userservice.dto;

import com.example.userservice.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Boolean status;
    private Role role;

}
