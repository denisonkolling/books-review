package com.example.booksreview.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
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
  public ResponseEntity<Book> createBook(@RequestBody CreateBookDTO createBookDTO) {
    return new ResponseEntity<Book>(bookService.createBook(createBookDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> findAllBooks() {
    return new ResponseEntity<List<BookDTO>>(bookService.findAllBooks(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {
    return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
  }
  

}