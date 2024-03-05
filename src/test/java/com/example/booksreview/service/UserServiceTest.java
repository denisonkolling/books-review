package com.example.booksreview.service;

import com.example.booksreview.dto.CreateUserDTO;
import com.example.booksreview.dto.UserDTO;
import com.example.booksreview.model.User;
import com.example.booksreview.repository.UserRepository;
import com.example.booksreview.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // Mock encoder de senha
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("password")).thenReturn(encodedPassword);

        // Mock dados do usuario
        CreateUserDTO createUserDTO = new CreateUserDTO("test@example.com", "Test User", "password");
        User savedUser = new User(createUserDTO, encodedPassword);

        // Mock metodo save do repository
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Chamada de metodo a ser testado
        UserDTO userDTO = userService.createUser(createUserDTO);

        // Verifica se user repository save foi chamado com parametros corretos
        verify(userRepository, times(1)).save(any(User.class));

        // Verifica se user password encode foi chamado com parametros corretos
        verify(passwordEncoder, times(1)).encode("password");

        // Verifica se userDTO contem dados esperados
        assertEquals(createUserDTO.email(), userDTO.email());
        assertEquals(createUserDTO.name(), userDTO.name());
        assertEquals(savedUser.getGuid(), userDTO.guid());

    }
}