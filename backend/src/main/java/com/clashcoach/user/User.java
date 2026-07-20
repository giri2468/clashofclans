package com.clashcoach.user;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name = "app_users")
public class User {
    @Id private UUID id;
    @Column(nullable = false, unique = true) private String email;
    @Column(name = "password_hash", nullable = false) private String passwordHash;
    @Column(name = "created_at", nullable = false) private Instant createdAt;
    @Column(name = "updated_at", nullable = false) private Instant updatedAt;
    protected User() { }
    public User(String email, String passwordHash) { this.id = UUID.randomUUID(); this.email = email; this.passwordHash = passwordHash; this.createdAt = Instant.now(); this.updatedAt = this.createdAt; }
    public UUID getId() { return id; } public String getEmail() { return email; } public String getPasswordHash() { return passwordHash; }
}
