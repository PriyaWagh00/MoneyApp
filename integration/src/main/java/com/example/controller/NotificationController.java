package com.example.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Notification;
import com.example.repository.NotificationRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // Fetch unread notifications
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable Long userId) {
        try {
            List<Notification> notifications = notificationRepository.findByUserIdAndIsReadFalse(userId);
            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error: " + e.getMessage()));
        }
    }


    // Mark notifications as read
    @PutMapping("/mark-as-read/{userId}")
    public ResponseEntity<?> markNotificationsAsRead(@PathVariable Long userId) {
        try {
            List<Notification> notifications = notificationRepository.findByUserIdAndIsReadFalse(userId);
            for (Notification notification : notifications) {
                notification.setRead(true);
            }
            notificationRepository.saveAll(notifications);
            return ResponseEntity.ok(Collections.singletonMap("message", "Notifications marked as read."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error: " + e.getMessage()));
        }
    }
}
