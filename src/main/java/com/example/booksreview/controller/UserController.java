package com.example.booksreview.controller;

import org.springframework.web.bind.annotation.*;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO user) {
    return new ResponseEntity<UserDTO>(userService.createUser(user), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> get(@PathVariable("id") String guid) {
    UserDTO response = this.userService.getByGuid(guid);
    return ResponseEntity.ok(response);
  }

}
