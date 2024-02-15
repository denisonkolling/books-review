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

}
