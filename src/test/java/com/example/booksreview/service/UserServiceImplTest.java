package com.example.booksreview.service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.UserRepository;
import com.example.booksreview.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Test
    void testCreateUser() {

        /* Arrange */
        CreateUserDTO createUserDTO = new CreateUserDTO("test@example.com", "Test User", "password");
        String encodedPassword = this.passwordEncoder.encode(createUserDTO.password());
        User savedUser = new User(createUserDTO, encodedPassword);

        /* Act*/
        userService.createUser(createUserDTO);
        verify(userRepository).save(userCaptor.capture());
        User createdUser = userCaptor.getValue();

        /* Assertions */
        assertEquals(savedUser.getEmail(), createdUser.getEmail());
        assertEquals(savedUser.getName(), createdUser.getName());
        assertEquals(savedUser.getPassword(), createdUser.getPassword());
    }
}