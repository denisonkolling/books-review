package com.example.booksreview.dto;

import com.example.booksreview.model.Review;

public record ReviewDTO(
        String bookName,
        Long id,
        Integer rate) {

    public ReviewDTO(Review review) {
        this(review.getBook().getTitle(), review.getId(), review.getRate());
    }
}
