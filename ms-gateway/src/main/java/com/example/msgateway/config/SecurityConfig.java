package com.example.msgateway.config;//package com.example.msauthservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/api/auth/login", "/api/auth/register").permitAll()

                        // Order endpoints - only USER
                        .requestMatchers("/orders/**").hasAuthority("USER")

                        // Role change - only ADMIN
                        .requestMatchers("/users/role/change/**").hasAuthority("ADMIN")

                        // Payments - USER or ADMIN
                        .requestMatchers("/payments/**").hasAnyAuthority("USER", "ADMIN")

                        // Default for user endpoints
                        .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")

                        // All others
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
