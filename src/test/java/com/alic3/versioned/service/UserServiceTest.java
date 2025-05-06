package com.alic3.versioned.service;

import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.model.User;
import com.alic3.versioned.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserServiceTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);

    }


    @Test
    void createUser_success() {

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("user");
        createUserRequest.setEmail("<EMAIL>");
        createUserRequest.setPassword("<PASSWORD>");

        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());
        when(userRepository.findByEmail("<EMAIL>")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(passwordEncoder.encode("<PASSWORD>")).thenReturn("encodedPassword");


        User user = userService.createUser(createUserRequest);


        assertNotNull(user);
        assertEquals("user", user.getUsername());
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("USER", user.getRole());


    }


    @Test
    void createUser_emailAlreadyExists() {

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("user");
        createUserRequest.setEmail("<EMAIL>");
        createUserRequest.setPassword("<PASSWORD>");

        when(userRepository.findByUsername("user")).thenReturn(Optional.ofNullable(new User()));

        assertThrows(RuntimeException.class, () -> userService.createUser(createUserRequest));

    }
}