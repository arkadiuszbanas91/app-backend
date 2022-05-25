package com.arkadiuszbanas.app.backend.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserVerifierTest {

    @Test
    void validUsernameNoThrow() {
        final UserVerifier userVerifier = new UserVerifier();

        final User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEnabled(true);

        assertDoesNotThrow(() -> userVerifier.verifyUser(user));

        user.setUsername("numbers123allowed");
        assertDoesNotThrow(() -> userVerifier.verifyUser(user));

        user.setUsername("andBIGLETTERS");
        assertDoesNotThrow(() -> userVerifier.verifyUser(user));

        user.setUsername("and_becausewhynot");
        assertDoesNotThrow(() -> userVerifier.verifyUser(user));
    }

    @Test
    void invalidUsernameThrows() {
        final UserVerifier userVerifier = new UserVerifier();

        final User user = new User();
        user.setUsername("");
        user.setPassword("password");
        user.setEnabled(true);

        assertThrows(IllegalArgumentException.class, () -> userVerifier.verifyUser(user));

        user.setUsername("no.special+characters#allowed@");
        assertThrows(IllegalArgumentException.class, () -> userVerifier.verifyUser(user));

        user.setUsername("even.dot");
        assertThrows(IllegalArgumentException.class, () -> userVerifier.verifyUser(user));

        user.setUsername(" a n d  w h i t e s p a c e  c h a r s");
        assertThrows(IllegalArgumentException.class, () -> userVerifier.verifyUser(user));
    }

    @Test
    void emptyPasswordThrows() {
        final UserVerifier userVerifier = new UserVerifier();

        final User user = new User();
        user.setUsername("user");
        user.setPassword("");
        user.setEnabled(true);

        assertThrows(IllegalArgumentException.class, () -> userVerifier.verifyUser(user));
    }

    @Test
    void validPasswordNoThrow() {
        final UserVerifier userVerifier = new UserVerifier();

        final User user = new User();
        user.setUsername("user");
        user.setPassword(" s p 1213%$#!^*TY");
        user.setEnabled(true);

        assertDoesNotThrow(() -> userVerifier.verifyUser(user));
    }
}