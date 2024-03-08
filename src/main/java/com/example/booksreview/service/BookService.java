package com.example.booksreview.service;

import java.util.List;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.dto.ReviewDTO;
import com.example.booksreview.model.Book;
import org.springframework.security.core.userdetails.UserDetails;

public interface BookService {

  BookDTO createBook(CreateBookDTO createBookDTO, UserDetails userInSession);

  List<BookDTO> findAllBooks();

  BookDTO findBookById(Long id);

  ReviewDTO createBookReview(CreateReviewDTO createReviewDTO, UserDetails userInSession);
}
