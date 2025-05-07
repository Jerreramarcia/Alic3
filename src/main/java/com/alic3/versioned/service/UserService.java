package com.alic3.versioned.service;

import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.model.User;

public interface UserService {

    User createUser(CreateUserRequest createUserRequest);

    User getUserById(Long id);

    User getUserByUsername(String username);
}
