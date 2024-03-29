package com.example.booksreview.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "user_guid", referencedColumnName = "guid", nullable = false)
    private User user;

    @JsonBackReference
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

    @Override
    public String toString() {
      return "Review [id=" + id + ", rate=" + rate + ", user=" + user + ", book=" + book + "]";
    }

    
}
