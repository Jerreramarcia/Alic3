package com.alic3.versioned.controller;


import com.alic3.versioned.constants.ControllerConstants;
import com.alic3.versioned.dto.User.CreateUserRequest;
import com.alic3.versioned.model.User;
import com.alic3.versioned.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ControllerConstants.MAPPING_USER)
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {


    private final UserService userService;


    @GetMapping("/{id}")
    public User getUsersById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return new UserDto(userService.createUser(createUserRequest));
    }


    record UserDto(User user) {
    }
}
