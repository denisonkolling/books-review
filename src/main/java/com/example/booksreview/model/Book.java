package com.example.booksreview.model;

import jakarta.persistence.*;

import java.util.List;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;

@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    private User user;

    private Integer year;

    @OneToMany
    private List<Review> reviews;

    public Book() {
    }

    public Book(Long id, String title, User user, Integer year, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.year = year;
        this.reviews = reviews;
    }

    public Book(CreateBookDTO createBookDTO) {
        this.title = createBookDTO.title();
        this.year = createBookDTO.year();
    }

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.id();
        this.title = bookDTO.title();
        this.year = bookDTO.year();
        this.reviews = bookDTO.reviews();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
