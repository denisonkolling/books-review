package com.example.booksreview.model;


import java.util.UUID;

import com.example.booksreview.dto.CreateUserDTO;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    private String guid;

    private String name;

    private String email;

    private String password;

    public User() {
    }

    public User(CreateUserDTO createUserDTO) {
        this.guid = UUID.randomUUID().toString();
        this.name = createUserDTO.name();
        this.email = createUserDTO.email();
        this.password = createUserDTO.password();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
