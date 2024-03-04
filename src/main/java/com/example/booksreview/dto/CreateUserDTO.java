package com.example.booksreview.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
    @NotBlank String email,
    @NotBlank String name,
    @NotBlank String password) {
}
