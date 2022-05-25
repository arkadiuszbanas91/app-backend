package com.arkadiuszbanas.app.backend.user;

import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserVerifier userVerifier = new UserVerifier();

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(@NonNull String username) {
        return userRepository.findById(username).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by("username"));
    }

    public User addUser(@NonNull User user) {
        userVerifier.verifyUser(user);
        if (userRepository.existsById(user.getUsername())) {
            throw new EntityExistsException(String.format("User '%s' already exists.", user.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public User enableUser(@NonNull String username, @NonNull boolean enable) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException(String.format("User '%s' not found.", username));
        }

        final User user = userRepository.findUserByUsername(username);
        user.setEnabled(enable);
        return userRepository.saveAndFlush(user);
    }

    public void removeUser(String username) {
        if (!userRepository.existsById(username)) {
            throw new EntityNotFoundException(String.format("User '%s' not found.", username));
        }
        userRepository.deleteById(username);
    }
}
