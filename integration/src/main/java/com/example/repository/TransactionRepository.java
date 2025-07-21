package com.example.repository;

import com.example.entity.Transaction;
import com.example.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    List<Transaction> findBySender_IdOrReceiver_Id(Long senderId, Long receiverId);
////    List<Transaction> findBySender_IdOrReceiver_Id(Long senderId, Long receiverId);
//
//}

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySender_IdOrReceiver_Id(Long senderId, Long receiverId);
    
    // Check if the transaction already exists
    Optional<Transaction> findBySenderAndReceiverAndAmount(User sender, User receiver, Double amount);
}


