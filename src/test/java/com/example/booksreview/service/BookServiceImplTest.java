package com.example.booksreview.service;

import com.example.booksreview.dto.CreateBookDTO;
import com.example.booksreview.model.Book;
import com.example.booksreview.repository.BookRepository;
import com.example.booksreview.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Captor
    private ArgumentCaptor<Book> bookCaptor;

    @Test
    void testCreateBook() {

        /* Arrange */
        CreateBookDTO createBookDTO = new CreateBookDTO("Book Title Test", 2024);

        /* Act*/
        bookService.createBook(createBookDTO);
        verify(bookRepository).save(bookCaptor.capture());
        Book createdBook = bookCaptor.getValue();

        /* Assertions */
        assertEquals(createBookDTO.title(), createdBook.getTitle());
        assertEquals(createBookDTO.year(), createdBook.getYear());
    }
}