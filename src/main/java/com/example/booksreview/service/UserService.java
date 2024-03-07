package com.example.booksreview.service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.model.User;


public interface UserService {

  UserDTO createUser(CreateUserDTO createUserDTO);

  User findByEmail(String email);
  
}
