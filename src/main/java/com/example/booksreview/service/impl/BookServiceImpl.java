package com.example.booksreview.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.model.Review;
import com.example.booksreview.repository.BookRepository;
import com.example.booksreview.repository.ReviewRepository;
import com.example.booksreview.service.BookService;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final ReviewRepository reviewRepository;

  public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository) {
    this.bookRepository = bookRepository;
    this.reviewRepository = reviewRepository;
  }

  @Override
  public Book createBook(CreateBookDTO createBookDTO) {

      return this.bookRepository.save(new Book(createBookDTO));

  }

  @Override
  public List<BookDTO> findAllBooks() {

    List<Book> booksList = bookRepository.findAll();

    List<BookDTO> booksDTOList = new ArrayList<>();

    for (Book book : booksList) {
      BookDTO bookDTO = new BookDTO(book);
      booksDTOList.add(bookDTO);
    }

    return booksDTOList;

  }

  @Override
  public BookDTO findBookById(Long id) {

    Book bookDB = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

    return new BookDTO(bookDB);

  }

  @Override
  public Review createBookReview(CreateReviewDTO createReviewDTO) {
    
    Book book = bookRepository.findById(createReviewDTO.bookId())
        .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + createReviewDTO.bookId()));

    Review review = new Review();
    review.setBook(book);
    review.setRate(createReviewDTO.rate());

    reviewRepository.save(review);

    book.getReviews().add(review);

    return review;
  }

}
