package com.example.msauthservice.entities;

import com.example.msauthservice.enums.AuthStatus;
import com.example.msauthservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "t_auth_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private Long userId; // user-service -> user -> id

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthUser authUser)) return false;
        return Objects.equals(id, authUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}