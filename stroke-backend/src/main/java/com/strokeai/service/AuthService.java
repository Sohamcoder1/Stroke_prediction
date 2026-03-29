package com.strokeai.service;

import com.strokeai.model.User;
import java.util.List;

public interface AuthService {

    String register(User user);

    String login(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    String updateUser(Long id, User user);

    String deleteUser(Long id);
}