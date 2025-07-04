package com.example.rewards.service;

import com.example.rewards.entity.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private final TransactionRepository repository;

    public RewardService(TransactionRepository repository) {
        this.repository = repository;
    }

    /**
     * Calculates the monthly reward points for a customer based on their transactions.
     *
     * @param customerId the ID of the customer
     * @return a map of month (e.g., "Apr", "May") to reward points earned
     */
    public Map<String, Integer> getMonthlyRewards(String customerId) {
        List<Transaction> transactions = repository.findByCustomerId(customerId);

        return transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                        Collectors.summingInt(t -> calculatePoints(t.getAmount()))
                ));
    }

    /**
     * Calculates the total reward points earned by a customer across all months.
     *
     * @param customerId the ID of the customer
     * @return the total reward points earned
     */
    public int getTotalRewards(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .mapToInt(t -> calculatePoints(t.getAmount()))
                .sum();
    }

    /**
     * Calculates reward points for a single transaction amount.
     * <ul>
     *     <li>2 points for every dollar over $100</li>
     *     <li>1 point for every dollar between $50 and $100</li>
     * </ul>
     *
     * @param amount the transaction amount
     * @return reward points earned for the given amount
     */
    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * ((int) amount - 100);
            points += 50;
        } else if (amount > 50) {
            points += ((int) amount - 50);
        }
        return points;
    }
}
