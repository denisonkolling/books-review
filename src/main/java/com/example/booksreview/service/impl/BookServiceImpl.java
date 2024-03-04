package com.example.booksreview.service.impl;

import org.springframework.stereotype.Service;

import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.repository.BookRepository;
import com.example.booksreview.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  private BookRepository bookRepository;

  public BookServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book createBook(CreateBookDTO createBookDTO) {

    Book book = this.bookRepository.save(new Book(createBookDTO));

    return book;

  }

}
