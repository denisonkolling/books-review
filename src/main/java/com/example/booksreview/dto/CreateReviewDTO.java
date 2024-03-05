package com.example.booksreview.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReviewDTO(
        @NotBlank Long bookId,
        @NotBlank Integer rate
) {
}
