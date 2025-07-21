package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/forget-password")
    public String forgetPassword(@RequestParam String email) {
        Optional<User> userOptional = userService.findByEmail(email);

        if (userOptional.isEmpty()) {
            return "User with this email does not exist.";
        }

        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        userService.save(user);

        // Simulate sending email (In real application, use an email service)
//        return "A reset link has been sent to your email: " + email + ". Reset token: " + resetToken;
        
        return "A reset link has been sent to your email: " + email + ". Get Reset Token From Databases: ";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<User> userOptional = userService.findByResetToken(token);

        if (userOptional.isEmpty()) {
            return "Invalid reset token.";
        }

        User user = userOptional.get();
        user.setPassword(newPassword); // In real application, hash the password
        user.setResetToken(null);
        userService.save(user);

        return "Password reset successful.";
    }
}
