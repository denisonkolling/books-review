package com.example.booksreview.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    public Review() {
    }

    public Review(Long id, Integer rate, User user, Book book) {
        this.id = id;
        this.rate = rate;
        this.user = user;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
