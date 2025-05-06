package com.alic3.versioned.service;


import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.model.User;
import com.alic3.versioned.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserService interface, responsible for managing user-related operations.
 * Provides functionalities to create new users and retrieve existing ones.
 * <p>
 * This class interacts directly with the UserRepository for persistence operations
 * and encapsulates the domain logic for handling users.
 * <p>
 * Annotations:
 * - @Data: Generates boilerplate code such as getters, setters, equals, hashCode, and toString.
 * - @AllArgsConstructor: Generates a constructor with all declared fields as parameters.
 * - @Service: Marks this class as a Spring service, enabling component scanning and dependency injection.
 * <p>
 * Thread-Safety:
 * - This class uses the @Transactional annotation to ensure consistency for transactional operations.
 */
@Data
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User createUser(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists with username: " + request.getUsername());
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + request.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.builder().username(request.getUsername()).email(request.getEmail()).password(encodedPassword).role("USER").build();
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);

    }

}