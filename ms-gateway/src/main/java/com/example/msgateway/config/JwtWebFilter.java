package com.example.msgateway.config;

import com.example.msgateway.enums.Role;
import com.example.msgateway.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtWebFilter implements WebFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtWebFilter.class);
    private final UserService userService;
    private static final String SECRET_KEY = "d36f5e8c61a9c64c38682a162e3460873dcf995a183be7f7c206eefce1a9bb98a";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("Filter started path: {}", exchange.getRequest().getPath());
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        log.info("Auth header: {}", authHeader);
        String path = request.getURI().getPath();
        log.info("Path: {}", path);
        if (path.startsWith("/api/auth/")) {
            log.info("path starts with /api/auth. chain filter");
            return chain.filter(exchange); // bu yollar üçün token yoxlanılmasın
        }


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("Auth header is invalid. chain filter");
            return chain.filter(exchange);
        }

        String token = authHeader.substring(7);
        log.info("token: {}", token);

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();
            String username = claims.get("username", String.class);

            Role role = userService.getUserRole(Long.parseLong(userId));
            log.info("user role: {}, user id: {}, user username: {}", role, userId, username);

            var authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority(role.name()))
            );
            log.info("authentication: {}", authentication);

            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(
                            Mono.just(new SecurityContextImpl(authentication)))
                    );

        } catch (Exception e) {
            log.error("error occurred to filter: {}", e.getMessage());
            e.printStackTrace();
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
