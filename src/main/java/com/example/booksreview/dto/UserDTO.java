package com.example.booksreview.dto;

import com.example.booksreview.model.User;

public record UserDTO(
    String guid,
    String email,
    String name) {

  public UserDTO(User user) {
    this(user.getGuid(), user.getEmail(), user.getName());
  }

}