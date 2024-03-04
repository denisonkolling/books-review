package com.example.booksreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booksreview.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
  
}
