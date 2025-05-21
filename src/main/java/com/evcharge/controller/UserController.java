package com.evcharge.controller;

import com.evcharge.dto.LoginRequest;
import com.evcharge.model.User;
import com.evcharge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Spring Boot!";
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User existing = userRepository.findByEmail(loginRequest.getEmail());
        if (!loginRequest.getPassword().equals(existing.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        if (existing != null) {
            return ResponseEntity.ok(existing);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}