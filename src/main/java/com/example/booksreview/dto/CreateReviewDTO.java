package com.example.booksreview.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReviewDTO(
    @NotBlank Integer rate,
    @NotBlank Long bookId) {
}
