package com.alic3.versioned.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Represents a request to create a new user. This class holds the necessary information
 * required for creating a user account, including the username, email, and password.
 *
 * The fields in this class are validated to ensure they meet the required constraints:
 * - Username cannot be blank.
 * - Email must follow a valid email format and cannot be blank.
 * - Password cannot be blank.
 *
 * This class is typically used as a data transfer object in user management operations.
 */
@Data
public class CreateUserRequest {


    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}