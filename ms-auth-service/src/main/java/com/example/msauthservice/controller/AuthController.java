package com.example.msauthservice.controller;

import com.example.msauthservice.dto.AuthResponse;
import com.example.msauthservice.dto.LoginRequest;
import com.example.msauthservice.dto.RegisterRequest;
import com.example.msauthservice.enums.Role;
import com.example.msauthservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/user/role/{id}")
    public Role getUserRole(@PathVariable Long id) {
        return authService.getRoleForUser(id);
    }

    @DeleteMapping("/user/role-cache-evict/{id}")
    public void evictUserRoleCache(@PathVariable Long id) {
        authService.evictRoleFromCache(id);
    }
}
