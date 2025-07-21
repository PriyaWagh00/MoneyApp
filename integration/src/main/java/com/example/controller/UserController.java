
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.example.entity.LoginRequest;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.TransactionService;
import com.example.service.UserService;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.entity.User;
import com.example.service.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

   
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestBody User user, BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            String errorMessage = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }

      
        if (user.getEmail() == null || !user.getEmail().endsWith("@gmail.com")) {
            return ResponseEntity.badRequest().body("Only Gmail email addresses are allowed");
        }

       
        Optional<User> existingUserByEmail = userService.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // Check if the mobile number already exists
        Optional<User> existingUserByMobile = userService.findByMobile(user.getMobile());
        if (existingUserByMobile.isPresent()) {
            return ResponseEntity.badRequest().body("Mobile number already exists");
        }

        // Set default role and save the user
        user.setRole("USER");
        userService.saveUser(user);
        return ResponseEntity.ok("Signup successful");
    }

 
    
    
    
    // Endpoint for user login
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        Optional<User> existingUser = userService.findByEmail(user.getEmail());
//        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
//            // If user credentials are valid, return user details
//            return ResponseEntity.ok(existingUser.get());
//        }
//        return ResponseEntity.badRequest().body("Invalid credentials");
//    }
//    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            // If user credentials are valid, return user details including balance
            User loggedInUser = existingUser.get();
            
            // Return the full user data including balance
            Map<String, Object> response = new HashMap<>();
            response.put("id", loggedInUser.getId());
            response.put("name", loggedInUser.getName());
            response.put("role", loggedInUser.getRole());
            response.put("balance", loggedInUser.getBalance());  // Include the balance
            
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    

    @GetMapping("/get-all-users")
    public ResponseEntity<List<Map<String, String>>> getAllUsers() {
        List<Map<String, String>> users = userService.getAllUsers().stream()
                .map(user -> Map.of(
                        "name", user.getName(),
                        "mobile", user.getMobile()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}/balance")
    public ResponseEntity<?> getUserBalance(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            return ResponseEntity.ok(Collections.singletonMap("balance", user.getBalance()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    
//    @GetMapping("/get-username-by-mobile/{mobile}")
//    public ResponseEntity<?> getUsernameByMobile(@PathVariable String mobile) {
//        Optional<User> user = userService.findByMobile(mobile);
//        if (user.isPresent()) {
//            return ResponseEntity.ok(Collections.singletonMap("name", user.get().getName()));
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for the given mobile number.");
//    }

    
    @GetMapping("/get-username-by-mobile/{mobile}")
    public ResponseEntity<?> getUsernameByMobile(@PathVariable String mobile) {
        try {
            // Ensure the mobile number starts with +91
            String normalizedMobile = mobile.startsWith("+91") ? mobile : "+91 " + mobile.trim();

            // Log the normalized number for debugging
            System.out.println("Searching for Mobile: " + normalizedMobile);

            Optional<User> user = userService.findByMobile(normalizedMobile);
            if (user.isPresent()) {
                return ResponseEntity.ok(Collections.singletonMap("name", user.get().getName()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }


    
}

