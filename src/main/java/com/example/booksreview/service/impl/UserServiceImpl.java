package com.example.booksreview.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.UserRepository;
import com.example.booksreview.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDTO createUser(CreateUserDTO createUserDTO) {

    String passwordEncoded = this.passwordEncoder.encode(createUserDTO.password());

    User user = this.userRepository.save(new User(createUserDTO, passwordEncoded));
    
    return new UserDTO(user);
  }



}