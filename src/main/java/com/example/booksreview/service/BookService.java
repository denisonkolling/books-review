package com.example.booksreview.service;

import java.util.List;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.model.Review;

public interface BookService {

  Book createBook(CreateBookDTO createBookDTO);

  List<BookDTO> findAllBooks();

  BookDTO findBookById(Long id);

  Review createBookReview(CreateReviewDTO createReviewDTO);
}
