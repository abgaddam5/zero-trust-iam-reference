package com.abhishek.zerotrustiam.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PasswordPolicyService {
    private static final List<String> COMMON_PASSWORDS = List.of("password", "password123", "qwerty", "admin", "letmein");

    public void validate(String password, String email) {
        if (password == null || password.length() < 12) {
            throw new IllegalArgumentException("Password must be at least 12 characters long.");
        }
        String normalized = password.toLowerCase();
        if (COMMON_PASSWORDS.stream().anyMatch(normalized::contains)) {
            throw new IllegalArgumentException("Password contains a commonly used weak pattern.");
        }
        if (email != null) {
            String localPart = email.split("@")[0].toLowerCase();
            if (!localPart.isBlank() && normalized.contains(localPart)) {
                throw new IllegalArgumentException("Password must not contain the email username.");
            }
        }
    }
}
