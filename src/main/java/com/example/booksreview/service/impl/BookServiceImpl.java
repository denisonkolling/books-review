package com.example.booksreview.service.impl;


import com.example.booksreview.dto.ReviewDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.model.Review;
import com.example.booksreview.repository.BookRepository;
import com.example.booksreview.repository.ReviewRepository;
import com.example.booksreview.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final ReviewRepository reviewRepository;

  private final UserService userService;

  public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository, UserService userService) {
    this.bookRepository = bookRepository;
    this.reviewRepository = reviewRepository;
    this.userService = userService;
  }

  @Override
  public Book createBook(CreateBookDTO createBookDTO, UserDetails userInSession) {

    User user = this.userService.findByEmail(userInSession.getUsername());

      return this.bookRepository.save(new Book(createBookDTO, user));

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
  public ReviewDTO createBookReview(CreateReviewDTO createReviewDTO, UserDetails userInSession) throws RuntimeException {
    
    Book book = bookRepository.findById(createReviewDTO.bookId())
        .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + createReviewDTO.bookId()));

    User user = this.userService.findByEmail(userInSession.getUsername());

    if (book.getReviews() == null) {
      book.setReviews(new ArrayList<>());
    }

    Review review = new Review();
    review.setBook(book);
    review.setRate(createReviewDTO.rate());
    review.setUser(user);

    reviewRepository.save(review);

    book.getReviews().add(review);

    return new ReviewDTO(review);
  }

}
