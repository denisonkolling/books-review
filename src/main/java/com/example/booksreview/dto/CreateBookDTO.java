package com.example.booksreview.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateBookDTO(
    @NotBlank String title,
    @NotBlank Integer year
    ) {
}

