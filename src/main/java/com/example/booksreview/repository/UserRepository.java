package com.example.booksreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booksreview.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
