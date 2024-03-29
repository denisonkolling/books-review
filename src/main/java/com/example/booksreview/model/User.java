package com.example.booksreview.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.booksreview.dto.CreateUserDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String guid;

    private String name;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private boolean enabled;

    public User() {
    }

    public User(CreateUserDTO createUserDTO, String password) {
        this.guid = UUID.randomUUID().toString();
        this.name = createUserDTO.name();
        this.email = createUserDTO.email();
        this.createdAt = LocalDateTime.now();
        this.password = password;
        this.enabled = true;
    }

    public User(String guid, String name, String email, String password, LocalDateTime createdAt, boolean enabled) {
        this.guid = guid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.enabled = true;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.createdAt = LocalDateTime.now();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
