package com.strokeai.service;

import com.strokeai.model.User;
import com.strokeai.repository.UserRepository;
import com.strokeai.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(User user) {

        user.setId(null); // 🔥 FIX
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && PasswordUtil.matchPassword(password, user.getPassword())) {
            return "Login Success";
        }

        return "Login Failed";
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}