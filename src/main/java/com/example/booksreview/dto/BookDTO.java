package com.example.booksreview.dto;

import java.util.List;

import com.example.booksreview.model.Book;
import com.example.booksreview.model.Review;

public record BookDTO(
    Long id,
    String title,
    Integer year,
    List<Review> reviews) {

  public BookDTO(Book book) {
    this(book.getId(), book.getTitle(), book.getYear(), book.getReviews());
  }
}