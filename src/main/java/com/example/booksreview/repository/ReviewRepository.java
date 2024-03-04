package com.example.booksreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booksreview.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
  
}
