package com.example.booksreview.service.impl;

import org.springframework.stereotype.Service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.UserRepository;
import com.example.booksreview.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO createUser(CreateUserDTO createUserDTO) {

    User user = this.userRepository.save(new User(createUserDTO));
    
    return new UserDTO(user);
  }

}