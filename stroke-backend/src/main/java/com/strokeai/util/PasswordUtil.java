package com.strokeai.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 🔐 Encrypt password
    public static String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // ✅ Match password
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}