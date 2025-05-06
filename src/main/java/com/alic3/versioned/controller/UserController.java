package com.alic3.versioned.controller;


import com.alic3.versioned.constants.ControllerConstants;
import com.alic3.versioned.model.User;
import com.alic3.versioned.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = ControllerConstants.MAPPING_USER)
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {


    private final UserService userService;


    @GetMapping("/{id}")
    public User getAllUsers(@PathVariable Long id) {
        return userService.getUser(id);
    }


    record UserDto(User user) {
    }
}
