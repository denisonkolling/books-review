package com.example.booksreview.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }
  
  
}
