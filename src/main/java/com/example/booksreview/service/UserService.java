package com.example.booksreview.service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;


public interface UserService {

  UserDTO createUser(CreateUserDTO createUserDTO);
  
}
