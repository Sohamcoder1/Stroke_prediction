package com.strokeai.controller;

import com.strokeai.model.User;
import com.strokeai.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authService.login(user.getEmail(), user.getPassword());
    }

    // ✅ GET USER BY ID
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return authService.getUserById(id);
    }
}