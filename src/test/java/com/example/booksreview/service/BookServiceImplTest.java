package com.example.booksreview.service;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.dto.CreateReviewDTO;
import com.example.booksreview.dto.ReviewDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.model.Review;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.BookRepository;
import com.example.booksreview.repository.ReviewRepository;
import com.example.booksreview.service.impl.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private ReviewRepository reviewRepository;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    @DisplayName("Should create book with success")
    void shouldCreateBookWithSucess() {

        /* Arrange */
        CreateBookDTO createBookDTO = new CreateBookDTO("Book Title Test", 2024);

        String email = "user@example.com";
        User user = new User();
        user.setEmail(email);

        /* Act*/
        bookService.createBook(createBookDTO, user);
        verify(bookRepository).save(bookCaptor.capture());
        Book createdBook = bookCaptor.getValue();

        /* Assertions */
        assertEquals(createBookDTO.title(), createdBook.getTitle());
        assertEquals(createBookDTO.year(), createdBook.getYear());
    }

    @Nested
    class ListBooksTest {
        @Test
        @DisplayName("Return all books with success when repository is not empty")
        void returnAllBooksWithSuccessWhenRepositoryIsNotEmpty() {
            /* Arrange */
            User user = new User("123abc", "John Doe", "john@example.com", "password123", LocalDateTime.now(), true);
            Book book = new Book(222L, "Book Title Test", user, 2024, null);
            List<Book> bookList = List.of(book);
            when(bookRepository.findAll()).thenReturn(bookList);

            /* Act*/
            List<BookDTO> output = bookService.findAllBooks();

            /* Assertions */
            assertNotNull(output);
            assertEquals(bookList.size(), output.size());
        }

        @Test
        @DisplayName("Return empty list when repository is empty")
        void returnEmptyListWhenRepositoryIsEmpty() {
            /* Arrange */
            List<Book> emptyBookList = Collections.emptyList();
            when(bookRepository.findAll()).thenReturn(emptyBookList);

            /* Act*/
            List<BookDTO> output = bookService.findAllBooks();

            /* Assertions */
            assertNotNull(output);
            assertTrue(output.isEmpty());
        }
    }

    @Nested
    class FindBookById {
        @Test
        @DisplayName("Return book by id with success")
        void getBookByIdWithSuccess() {
            // Arrange
            User user = new User("123abc", "John Doe", "john@example.com", "password123", LocalDateTime.now(), true);
            Book book = new Book(222L, "Book Title Test", user, 2024, null);

            when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

            // Act
            BookDTO output = bookService.findBookById(book.getId());

            // Assert
            assertEquals(book.getId(), output.id());
        }

        @Test
        @DisplayName("Throw exception when book id not found")
        void throwExceptionWhenBookIdNotFound() {
            // Arrange
            Long nonExistingId = 999L;
            when(bookRepository.findById(nonExistingId)).thenReturn(Optional.empty());

            // Act and Assert
            assertThrows(RuntimeException.class, () -> bookService.findBookById(nonExistingId));
        }
    }

    @Nested
    class BooksReview {

        @Test
        @DisplayName("Should create book review with success")
        void shouldCreateBookReviewWithSucess() {

            /* Arrange */
            CreateReviewDTO createReviewDTO = new CreateReviewDTO(1L, 5);
            Book book = new Book();
            book.setId(1L);
            book.setTitle("Book Teste");

            String email = "user@example.com";
            User user = new User();
            user.setEmail(email);

            when(bookRepository.findById(createReviewDTO.bookId())).thenReturn(Optional.of(book));

            /* Act*/
            ReviewDTO createdReview = bookService.createBookReview(createReviewDTO, user);


            /* Assertions */
            verify(reviewRepository).save(any(Review.class));
            assertEquals(createReviewDTO.bookId(), createdReview.id());
            assertEquals(createReviewDTO.rate(), createdReview.rate());

        }
    }

}
