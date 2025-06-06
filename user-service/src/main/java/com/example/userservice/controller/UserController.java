package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserRoleCacheDto;
import com.example.userservice.enums.Role;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Long add(@RequestBody UserDto dto) {
        return userService.add(dto);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/role/{id}")
    public UserRoleCacheDto getRole(@PathVariable Long id) {
        return userService.getRole(id);
    }

    @PutMapping("/role/change/{id}")
    public void changeRole(@PathVariable Long id, @RequestParam Role role) {
        userService.changeRole(id, role);
    }
}
