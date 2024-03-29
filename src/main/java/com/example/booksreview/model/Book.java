package com.example.booksreview.model;

import jakarta.persistence.*;

import java.util.List;

import com.example.booksreview.dto.BookDTO;
import com.example.booksreview.dto.CreateBookDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_guid", referencedColumnName = "guid", nullable = false)
    private User user;

    private Integer year;

    @JsonManagedReference
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

    public Book(CreateBookDTO createBookDTO, User user) {
        this.title = createBookDTO.title();
        this.year = createBookDTO.year();
        this.user = user;
    }

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.id();
        this.title = bookDTO.title();
        this.year = bookDTO.year();
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
