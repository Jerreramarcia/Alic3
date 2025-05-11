package com.alic3.versioned.controller;

import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.jwt.JwtUtil;
import com.alic3.versioned.jwt.auth.AuthRequest;
import com.alic3.versioned.jwt.auth.AuthResponse;
import com.alic3.versioned.security.UserDetailsImpl;
import com.alic3.versioned.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authManager;

    UserService userService;

    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal(); // aquí sí usas auth
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal UserDetailsImpl user) {
        return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "image", "https://api.dicebear.com/7.x/identicon/svg?seed=" + user.getUsername()
        );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserController.UserDto createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return new UserController.UserDto(userService.createUser(createUserRequest));
    }

}
