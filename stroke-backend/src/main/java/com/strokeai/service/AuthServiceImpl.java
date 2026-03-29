package com.strokeai.service;

import com.strokeai.model.User;
import com.strokeai.repository.UserRepository;
import com.strokeai.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public String register(User user) {
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        userRepo.save(user);
        return "Register Success";
    }

    @Override
    public String login(User user) {

        User dbUser = userRepo.findByEmail(user.getEmail());

        if (dbUser != null &&
                PasswordUtil.matchPassword(user.getPassword(), dbUser.getPassword())) {
            return "Login Success";
        }

        return "Login Failed";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public String updateUser(Long id, User updatedUser) {

        User user = userRepo.findById(id).orElse(null);

        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());

            if (updatedUser.getPassword() != null) {
                user.setPassword(
                        PasswordUtil.encryptPassword(updatedUser.getPassword())
                );
            }

            userRepo.save(user);
            return "User Updated Successfully";
        }

        return "User Not Found";
    }

    @Override
    public String deleteUser(Long id) {

        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return "User Deleted Successfully";
        }

        return "User Not Found";
    }
}