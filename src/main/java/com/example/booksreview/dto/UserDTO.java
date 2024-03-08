package com.example.booksreview.dto;

import com.example.booksreview.model.User;

public record UserDTO(
        String guid, String name, String email) {

  public UserDTO(User user) {
    this(user.getGuid(), user.getName(), user.getEmail());
  }

}