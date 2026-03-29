package com.strokeai.controller;

import com.strokeai.model.User;
import com.strokeai.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return authService.login(user);
    }

    // ✅ GET ALL USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    // ✅ GET USER BY ID
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return authService.getUserById(id);
    }

    // ✅ UPDATE USER
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return authService.updateUser(id, user);
    }

    // ✅ DELETE USER
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return authService.deleteUser(id);
    }
}