package com.strokeai.service;

import com.strokeai.model.User;

public interface AuthService {

    String register(User user);
    String login(String email, String password);
    User getUserById(Long id);
}