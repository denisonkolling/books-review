package com.example.booksreview.service;

import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.model.Book;

public interface BookService {
  
  Book createBook(CreateBookDTO createBookDTO);

}
