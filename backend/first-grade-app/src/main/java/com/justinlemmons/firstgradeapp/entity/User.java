package com.justinlemmons.firstgradeapp.entity;

import com.justinlemmons.firstgradeapp.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}
