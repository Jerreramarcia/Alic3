package com.alic3.versioned.service;


import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.model.User;
import com.alic3.versioned.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)// We are going to use it to clean h2
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser_persistsUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("ana");
        request.setEmail("ana@test.com");
        request.setPassword("1234");

        User user = userService.createUser(request);

        assertNotNull(user.getId());
        assertEquals("ana", user.getUsername());

        User found = userRepository.findById(user.getId()).orElseThrow();
        assertEquals("ana@test.com", found.getEmail());
        assertTrue(passwordEncoder.matches("1234", found.getPassword()));
    }
}

