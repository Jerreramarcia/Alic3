package com.alic3.versioned.jwt.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
