package com.example.controller;

import com.example.entity.Notification;
import com.example.entity.RechargePlan;
import com.example.entity.Transaction;
import com.example.entity.TransactionRequest;
import com.example.entity.User;
import com.example.repository.NotificationRepository;
import com.example.repository.RechargePlanRepository;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import com.example.service.TransactionService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
@Autowired
private NotificationRepository notificationRepository;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RechargePlanRepository rechargePlanRepository;    /**
     * Transfer money from one user to another.
     */
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        // Validate input
//        if (!request.containsKey("senderId") || !request.containsKey("receiverId") || !request.containsKey("amount")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid request. Please provide senderId, receiverId, and amount.");
//        }
//
//        try {
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            Long receiverId = Long.parseLong(request.get("receiverId").toString());
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than zero.");
//            }
//
//            transactionService.transferMoney(senderId, receiverId, amount);
//            return ResponseEntity.ok("Transaction successful.");
//        } catch (NumberFormatException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid number format: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An: " + e.getMessage());
//        }
//    }
    
    
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        if (!request.containsKey("senderId") || !request.containsKey("receiverId") || !request.containsKey("amount")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid request. Please provide senderId, receiverId, and amount.");
//        }
//
//        try {
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            Long receiverId = Long.parseLong(request.get("receiverId").toString());
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than zero.");
//            }
//
//            // Log before calling the service
//            System.out.println("Calling transferMoney for senderId: " + senderId + ", receiverId: " + receiverId + ", amount: " + amount);
//
//            // Perform the money transfer - this method already saves the transaction
//            transactionService.transferMoney(senderId, receiverId, amount);
//
//            // Log after the transfer
//            System.out.println("Transaction successful for senderId: " + senderId + ", receiverId: " + receiverId);
//
//            return ResponseEntity.ok("Transaction successful.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
//

//for mobile
    
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        if (!request.containsKey("senderId") || !request.containsKey("receiverMobile") || !request.containsKey("amount")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid request. Please provide senderId, receiverMobile, and amount.");
//        }
//
//        try {
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            String receiverMobile = request.get("receiverMobile").toString();
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than zero.");
//            }
//
//            // Find receiver user by mobile number
//         // Retrieve the receiver user by their mobile number
//            Optional<User> receiverUserOptional = userRepository.findByMobile(receiverMobile);
//
//            if (!receiverUserOptional.isPresent()) {
//                // If the receiver doesn't exist, return a "not found" response
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receiver with this mobile number not found.");
//            }
//
//            // If the receiver exists, retrieve the user
//            User receiverUser = receiverUserOptional.get();
//
//            // Proceed with further processing (such as validating and transferring money)
//
//            // Perform the money transfer using receiverId (from receiverMobile)
//            transactionService.transferMoney(senderId, receiverUser.getId(), amount);
//
//            return ResponseEntity.ok("Transaction successful.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }

    //both
    
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        // Validate the required parameters: senderId, amount, and either receiverId or receiverMobile
//        if (!request.containsKey("senderId") || !request.containsKey("amount") ||
//            (!request.containsKey("receiverId") && !request.containsKey("receiverMobile"))) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid request. Please provide senderId, receiverId or receiverMobile, and amount.");
//        }
//
//        try {
//            // Extract the sender ID and amount
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than zero.");
//            }
//
//            // Check if receiver is provided by ID or Mobile
//            User receiverUser = null;
//            if (request.containsKey("receiverId")) {
//                // Find receiver by ID
//                Long receiverId = Long.parseLong(request.get("receiverId").toString());
//                receiverUser = userRepository.findById(receiverId).orElse(null);
//            } else if (request.containsKey("receiverMobile")) {
//                // Find receiver by Mobile number
//                String receiverMobile = request.get("receiverMobile").toString();
//                receiverUser = userRepository.findByMobile(receiverMobile).orElse(null);
//            }
//
//            if (receiverUser == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receiver not found.");
//            }
//
//            // Perform the money transfer using the found receiver
//            transactionService.transferMoney(senderId, receiverUser.getId(), amount);
//
//            return ResponseEntity.ok("Transaction successful.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
    
    //main working transfer
    
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        // Validate the required parameters: senderId, amount, and either receiverId or receiverMobile
//        if (!request.containsKey("senderId") || !request.containsKey("amount") ||
//            (!request.containsKey("receiverId") && !request.containsKey("receiverMobile"))) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Invalid request. Please provide senderId, receiverId or receiverMobile, and amount.");
//        }
//
//        try {
//            // Extract the sender ID and amount
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than zero.");
//            }
//
//            // Check if receiver is provided by ID or Mobile
//            User receiverUser = null;
//            if (request.containsKey("receiverId")) {
//                // Find receiver by ID
//                Long receiverId = Long.parseLong(request.get("receiverId").toString());
//                receiverUser = userRepository.findById(receiverId).orElse(null);
//            } else if (request.containsKey("receiverMobile")) {
//                // Find receiver by Mobile number
//                String receiverMobile = request.get("receiverMobile").toString();
//                receiverUser = userRepository.findByMobile(receiverMobile).orElse(null);
//            }
//
//            if (receiverUser == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receiver not found. Please check the ID or mobile number.");
//            }
//
//            // Perform the money transfer using the found receiver
//            transactionService.transferMoney(senderId, receiverUser.getId(), amount);
//
//            return ResponseEntity.ok("Transaction successful.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
    
    
    
    
    
//    @PostMapping("/transfer")
//    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
//        if (!request.containsKey("senderId") || !request.containsKey("amount") ||
//            (!request.containsKey("receiverId") && !request.containsKey("receiverMobile"))) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(Collections.singletonMap("message", "Invalid request. Please provide senderId, receiverId or receiverMobile, and amount."));
//        }
//
//        try {
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            Double amount = Double.parseDouble(request.get("amount").toString());
//
//            if (amount <= 0) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Collections.singletonMap("message", "Amount must be greater than zero."));
//            }
//
//            User sender = userRepository.findById(senderId).orElse(null);
//            if (sender == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(Collections.singletonMap("message", "Sender not found."));
//            }
//
//            // Check if sender has sufficient funds
//            if (sender.getBalance() < amount) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                        .body(Collections.singletonMap("message", "Insufficient funds."));
//            }
//
//            // Find receiver
//            User receiver = null;
//            if (request.containsKey("receiverId")) {
//                Long receiverId = Long.parseLong(request.get("receiverId").toString());
//                receiver = userRepository.findById(receiverId).orElse(null);
//            } else if (request.containsKey("receiverMobile")) {
//                String receiverMobile = request.get("receiverMobile").toString();
//                receiver = userRepository.findByMobile(receiverMobile).orElse(null);
//            }
//
//            if (receiver == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(Collections.singletonMap("message", "Receiver not found. Please check the ID or mobile number."));
//            }
//
//            // Perform money transfer
//            sender.setBalance(sender.getBalance() - amount);
//            receiver.setBalance(receiver.getBalance() + amount);
//            userRepository.save(sender);
//            userRepository.save(receiver);
//
//            // Log the transaction
//            Transaction transaction = new Transaction();
//            transaction.setSender(sender);
//            transaction.setReceiver(receiver);
//            transaction.setAmount(amount);
//            transaction.setTimestamp(LocalDateTime.now());
//            transactionRepository.save(transaction);
//
//            return ResponseEntity.ok(Collections.singletonMap("message", "Transaction successful."));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("message", "Error: " + e.getMessage()));
//        }
//    }
//

    
    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody Map<String, Object> request) {
        if (!request.containsKey("senderId") || !request.containsKey("amount") ||
            (!request.containsKey("receiverId") && !request.containsKey("receiverMobile"))) {
            logFailedTransaction(request, "Invalid request. Missing senderId, receiverId, or amount.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Invalid request. Please provide senderId, receiverId or receiverMobile, and amount."));
        }

        try {
            Long senderId = Long.parseLong(request.get("senderId").toString());
            Double amount = Double.parseDouble(request.get("amount").toString());

            if (amount <= 0) {
                logFailedTransaction(request, "Amount must be greater than zero.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("message", "Amount must be greater than zero."));
            }

            User sender = userRepository.findById(senderId).orElse(null);
            if (sender == null) {
                logFailedTransaction(request, "Sender not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "Sender not found."));
            }

            if (sender.getBalance() < amount) {
                logFailedTransaction(request, "Insufficient funds.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("message", "Insufficient funds."));
            }

            // Find receiver
            User receiver = null;
            if (request.containsKey("receiverId")) {
                Long receiverId = Long.parseLong(request.get("receiverId").toString());
                receiver = userRepository.findById(receiverId).orElse(null);
            } else if (request.containsKey("receiverMobile")) {
                String receiverMobile = request.get("receiverMobile").toString();
                receiver = userRepository.findByMobile(receiverMobile).orElse(null);
            }

            if (receiver == null) {
                logFailedTransaction(request, "Receiver not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("message", "Receiver not found. Please check the ID or mobile number."));
            }

            // Perform money transfer
//            sender.setBalance(sender.getBalance() - amount);
//            receiver.setBalance(receiver.getBalance() + amount);
//            userRepository.save(sender);
//            userRepository.save(receiver);
//
//            // Log successful transaction
//            Transaction transaction = new Transaction();
//            transaction.setSender(sender);
//            transaction.setReceiver(receiver);
//            transaction.setAmount(amount);
//            transaction.setTimestamp(LocalDateTime.now());
//            transaction.setStatus("SUCCESS");
//            transactionRepository.save(transaction);
            
         // Perform money transfer
//            sender.setBalance(sender.getBalance() - amount);
//            receiver.setBalance(receiver.getBalance() + amount);
//            userRepository.save(sender);
//            userRepository.save(receiver);
            
         // Perform money transfer
            sender.setBalance(sender.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            userRepository.save(sender);
            userRepository.save(receiver);

            // Create notification for the receiver
            Notification notification = new Notification();
            notification.setUserId(receiver.getId());
            notification.setMessage("You received ₹" + amount + " from " + sender.getName() + "");
            notification.setTimestamp(LocalDateTime.now());
            notificationRepository.save(notification);



            // Log successful transaction
            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setAmount(amount);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setStatus("SUCCESS"); // Ensure status is set to SUCCESS
            transactionRepository.save(transaction);


            return ResponseEntity.ok(Collections.singletonMap("message", "Transaction successful."));
        } catch (Exception e) {
            logFailedTransaction(request, "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error: " + e.getMessage()));
        }
    }

//    private void logFailedTransaction(Map<String, Object> request, String reason) {
//        Transaction failedTransaction = new Transaction();
//        if (request.containsKey("senderId")) {
//            Long senderId = Long.parseLong(request.get("senderId").toString());
//            failedTransaction.setSender(userRepository.findById(senderId).orElse(null));
//        }
//
//        if (request.containsKey("receiverId")) {
//            Long receiverId = Long.parseLong(request.get("receiverId").toString());
//            failedTransaction.setReceiver(userRepository.findById(receiverId).orElse(null));
//        } else if (request.containsKey("receiverMobile")) {
//            String receiverMobile = request.get("receiverMobile").toString();
//            failedTransaction.setReceiver(userRepository.findByMobile(receiverMobile).orElse(null));
//        }
//
//        if (request.containsKey("amount")) {
//            failedTransaction.setAmount(Double.parseDouble(request.get("amount").toString()));
//        }
//
//        failedTransaction.setTimestamp(LocalDateTime.now());
//        failedTransaction.setStatus("FAILED");
//        failedTransaction.setReason(reason);
//        transactionRepository.save(failedTransaction);
//    }

    
    private void logFailedTransaction(Map<String, Object> request, String reason) {
        Transaction failedTransaction = new Transaction();
        
        if (request.containsKey("senderId")) {
            Long senderId = Long.parseLong(request.get("senderId").toString());
            failedTransaction.setSender(userRepository.findById(senderId).orElse(null));
        }
        
        if (request.containsKey("receiverId")) {
            Long receiverId = Long.parseLong(request.get("receiverId").toString());
            failedTransaction.setReceiver(userRepository.findById(receiverId).orElse(null));
        } else if (request.containsKey("receiverMobile")) {
            String receiverMobile = request.get("receiverMobile").toString();
            failedTransaction.setReceiver(userRepository.findByMobile(receiverMobile).orElse(null));
        }
        
        if (request.containsKey("amount")) {
            failedTransaction.setAmount(Double.parseDouble(request.get("amount").toString()));
        }

        failedTransaction.setTimestamp(LocalDateTime.now());
        failedTransaction.setStatus("FAILED");
        failedTransaction.setReason(reason); // Include failure reason
        transactionRepository.save(failedTransaction);
    }


    

//    @GetMapping("/history/{userId}")
//    public ResponseEntity<?> getUserTransactionHistory(@PathVariable Long userId) {
//        try {
//            // Fetch transaction history
//            List<Map<String, Object>> history = transactionService.getUserTransactions(userId);
//
//            if (history.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction history found.");
//            }
//            return ResponseEntity.ok(history);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
    
    
//    @GetMapping("/history/{userId}")
//    public ResponseEntity<?> getUserTransactionHistory(@PathVariable Long userId) {
//        try {
//            // Fetch transaction history
//            List<Map<String, Object>> history = transactionService.getUserTransactions(userId);
//
//            if (history.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction history found.");
//            }
//            return ResponseEntity.ok(history);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//        }
//    }
    
    
    @GetMapping("/history/{userId}")
    public ResponseEntity<?> getUserTransactionHistory(@PathVariable Long userId) {
        try {
            List<Map<String, Object>> history = transactionService.getUserTransactions(userId);

            if (history.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No transaction history found.");
            }

            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



    
    
    



    
}

