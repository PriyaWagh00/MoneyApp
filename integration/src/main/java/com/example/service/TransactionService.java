package com.example.service;

import com.example.entity.Transaction;
import com.example.entity.TransactionRequest;
import com.example.entity.User;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
//
@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Transfer money from one user to another.
     */
//    @Transactional
//    public void transferMoney(Long senderId, Long receiverId, Double amount) {
//        // Validate users
//        User sender = userRepository.findById(senderId)
//                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
//        User receiver = userRepository.findById(receiverId)
//                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
//
//        // Check sender balance
//        if (sender.getBalance() < amount) {
//            throw new IllegalArgumentException("Insufficient balance");
//        }
//
//        // Deduct from sender, add to receiver
//        sender.setBalance(sender.getBalance() - amount);
//        receiver.setBalance(receiver.getBalance() + amount);
//
//        // Save updated users
//        userRepository.save(sender);
//        userRepository.save(receiver);
//
//        // Create and save transaction
//        Transaction transaction = new Transaction();
//        transaction.setSender(sender);  // Set sender as the User entity
//        transaction.setReceiver(receiver);  // Set receiver as the User entity
//        transaction.setAmount(amount);
//        transaction.setTimestamp(LocalDateTime.now());
//
//        transactionRepository.save(transaction);
//    }

    
    @Transactional
    public void transferMoney(Long senderId, Long receiverId, Double amount) {
        // Validate users
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        // Check sender balance
        if (sender.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Deduct from sender, add to receiver
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // Save updated users
        userRepository.save(sender);
        userRepository.save(receiver);

        // Log transaction creation
        System.out.println("Creating transaction for senderId: " + senderId + ", receiverId: " + receiverId + ", amount: " + amount);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);  // Only save once
        System.out.println("Transaction saved with ID: " + transaction.getId());
    }



    
    /**
     * Get transaction history for a user.
     */
//    public List<Map<String, Object>> getUserTransactions(Long userId) {
//        List<Transaction> transactions = transactionRepository.findBySender_IdOrReceiver_Id(userId, userId);
//        List<Map<String, Object>> history = new ArrayList<>();
//
//        for (Transaction transaction : transactions) {
//            Map<String, Object> transactionDetails = new HashMap<>();
//            transactionDetails.put("transactionId", transaction.getId());
//            transactionDetails.put("amount", transaction.getAmount());
//            transactionDetails.put("timestamp", transaction.getTimestamp());
//
//            if (transaction.getSender().getId().equals(userId)) {
//                transactionDetails.put("type", "Sent");
//                transactionDetails.put("to", transaction.getReceiver().getName());
//            } else {
//                transactionDetails.put("type", "Received");
//                transactionDetails.put("from", transaction.getSender().getName());
//            }
//
//            history.add(transactionDetails);
//        }
//
//        return history;
//    }
    
    
    
//    public List<Map<String, Object>> getUserTransactions(Long userId) {
//        List<Transaction> transactions = transactionRepository.findBySender_IdOrReceiver_Id(userId, userId);
//        List<Map<String, Object>> history = new ArrayList<>();
//
//        for (Transaction transaction : transactions) {
//            Map<String, Object> transactionDetails = new HashMap<>();
//            transactionDetails.put("transactionId", transaction.getId());
//            transactionDetails.put("amount", transaction.getAmount());
//            transactionDetails.put("timestamp", transaction.getTimestamp());
//
//            if (transaction.getSender().getId().equals(userId)) {
//                transactionDetails.put("type", "Sent");
//                transactionDetails.put("to", transaction.getReceiver().getName());
//            } else {
//                transactionDetails.put("type", "Received");
//                transactionDetails.put("from", transaction.getSender().getName());
//            }
//
//            history.add(transactionDetails);
//        }
//
//        return history;
//    }

    
    
//    public List<Map<String, Object>> getUserTransactions(Long userId) {
//        List<Transaction> transactions = transactionRepository.findBySender_IdOrReceiver_Id(userId, userId);
//        Set<Long> seenTransactionIds = new HashSet<>();
//        List<Map<String, Object>> history = new ArrayList<>();
//
//        for (Transaction transaction : transactions) {
//            // Skip if this transaction has already been processed
//            if (seenTransactionIds.contains(transaction.getId())) {
//                continue;
//            }
//
//            Map<String, Object> transactionDetails = new HashMap<>();
//            transactionDetails.put("transactionId", transaction.getId());
//            transactionDetails.put("amount", transaction.getAmount());
//            transactionDetails.put("timestamp", transaction.getTimestamp());
//
//            if (transaction.getSender().getId().equals(userId)) {
//                transactionDetails.put("type", "Sent");
//                transactionDetails.put("to", transaction.getReceiver().getName());
//            } else {
//                transactionDetails.put("type", "Received");
//                transactionDetails.put("from", transaction.getSender().getName());
//            }
//
//            // Add this transaction ID to the set to prevent duplicates
//            seenTransactionIds.add(transaction.getId());
//
//            // Add the transaction details to the history
//            history.add(transactionDetails);
//        }
//
//        return history;
//    }
//

    public List<Map<String, Object>> getUserTransactions(Long userId) {
        // Fetch transactions where the user is either the sender or the receiver
        List<Transaction> transactions = transactionRepository.findBySender_IdOrReceiver_Id(userId, userId);

        // Use a set to track unique transaction IDs and avoid duplicates
        Set<Long> seenTransactionIds = new HashSet<>();
        List<Map<String, Object>> history = new ArrayList<>();

        for (Transaction transaction : transactions) {
            // Skip if this transaction has already been processed
            if (seenTransactionIds.contains(transaction.getId())) {
                continue;
            }

            Map<String, Object> transactionDetails = new HashMap<>();
            transactionDetails.put("transactionId", transaction.getId());
            transactionDetails.put("amount", transaction.getAmount());
            transactionDetails.put("timestamp", transaction.getTimestamp());
            transactionDetails.put("status", transaction.getStatus()); // Add transaction status
            transactionDetails.put("reason", transaction.getReason()); // Add failure reason if applicable

            // Determine whether the transaction was "Sent" or "Received"
            if (transaction.getSender().getId().equals(userId)) {
                transactionDetails.put("type", "Sent");
                transactionDetails.put("to", transaction.getReceiver().getName());
            } else {
                transactionDetails.put("type", "Received");
                transactionDetails.put("from", transaction.getSender().getName());
            }

            // Add this transaction ID to the set to prevent duplicates
            seenTransactionIds.add(transaction.getId());

            // Add the transaction details to the history list
            history.add(transactionDetails);
        }

        return history;
    }
 
    

    
    
}



