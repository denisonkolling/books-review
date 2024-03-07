package com.example.booksreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booksreview.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
