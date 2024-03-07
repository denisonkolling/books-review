package com.example.booksreview.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.UserRepository;
import com.example.booksreview.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDTO createUser(CreateUserDTO createUserDTO) {

    String passwordEncoded = this.passwordEncoder.encode(createUserDTO.password());

    User user = new User(createUserDTO, passwordEncoded);

    userRepository.save(user);
    
    return new UserDTO(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User by email not found: %s", username)));
  }

  @Override
  public User findByEmail(String email) throws RuntimeException {
    return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException(String.format("User by email not found: %s", email)));
  }


}