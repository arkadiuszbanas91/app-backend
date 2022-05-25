package com.arkadiuszbanas.app.backend.user;

import org.springframework.lang.NonNull;

import java.util.regex.Pattern;

public class UserVerifier {
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^\\w+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^.+$");

    public void verifyUser(@NonNull User user) {
        if (user.getUsername() == null || !USERNAME_PATTERN.matcher(user.getUsername()).matches()) {
            throw new IllegalArgumentException(
                    "User requires a non-empty username containing only letters, numbers and _.");
        }
        if (user.getPassword() == null || !PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
            throw new IllegalArgumentException("User requires a non-empty password.");
        }
    }
}
