package com.example.booksreview.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.service.BookService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/book")
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody CreateBookDTO createBookDTO, @AuthenticationPrincipal UserDetails userInSession) {
    return new ResponseEntity<Book>(bookService.createBook(createBookDTO, userInSession), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> findAllBooks() {
    return new ResponseEntity<List<BookDTO>>(bookService.findAllBooks(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> findBookById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
  }

  @PostMapping("/{id}/review")
  public ResponseEntity<?> createBookReview(@PathVariable Long id, @RequestBody CreateReviewDTO createReviewDTO, @AuthenticationPrincipal UserDetails userInSession) {
      return new ResponseEntity<>(bookService.createBookReview(createReviewDTO, userInSession), HttpStatus.CREATED);
  }
  
  

}