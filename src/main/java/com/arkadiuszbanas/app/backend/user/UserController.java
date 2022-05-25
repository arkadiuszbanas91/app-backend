package com.arkadiuszbanas.app.backend.user;

import com.arkadiuszbanas.app.backend.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authenticate")
    public User getCurrentUser(@AuthenticationPrincipal CustomUserDetails user) {
        if (user == null) {
            return null;
        }
        return userService.getUser(user.getUsername());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{username}")
    public User updateUser(@PathVariable String username, @RequestParam boolean enable) {
        return userService.enableUser(username, enable);
    }

    @DeleteMapping("/users/{username}")
    public void removeUser(@PathVariable String username) {
        userService.removeUser(username);
    }
}
