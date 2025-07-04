package com.example.rewards.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


import com.example.rewards.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerId(String customerId);
}
