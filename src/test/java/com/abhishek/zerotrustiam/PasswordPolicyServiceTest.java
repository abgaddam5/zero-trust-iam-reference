package com.abhishek.zerotrustiam;

import com.abhishek.zerotrustiam.service.PasswordPolicyService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyServiceTest {
    private final PasswordPolicyService service = new PasswordPolicyService();

    @Test
    void acceptsStrongPassword() {
        assertDoesNotThrow(() -> service.validate("VeryStrongPass123!", "user@example.com"));
    }

    @Test
    void rejectsShortPassword() {
        assertThrows(IllegalArgumentException.class, () -> service.validate("Short1!", "user@example.com"));
    }

    @Test
    void rejectsEmailUsernameInPassword() {
        assertThrows(IllegalArgumentException.class, () -> service.validate("userStrongPass123!", "user@example.com"));
    }
}
