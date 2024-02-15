package com.example.booksreview.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tb_books")
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
}
