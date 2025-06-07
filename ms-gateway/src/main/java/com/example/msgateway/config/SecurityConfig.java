package com.example.msgateway.config;//package com.example.msauthservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtWebFilter jwtWebFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange

//                                .pathMatchers("/**").permitAll()
                        // Public endpoints
                        .pathMatchers("/api/auth/login", "/api/auth/register").permitAll()

                        // Order endpoints - only USER
                        .pathMatchers("/orders/**").hasAuthority("USER")

                        // Role change - only ADMIN
                        .pathMatchers("/users/role/change/**").hasAuthority("ADMIN")

                        // Payments - USER or ADMIN
                        .pathMatchers("/payments/**").hasAnyAuthority("USER", "ADMIN")

                        // Default user endpoints
                        .pathMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")

                        // Fallback
                        .anyExchange().authenticated()
                )
                .addFilterAt(jwtWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}