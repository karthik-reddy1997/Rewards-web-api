package com.example.rewards.controller;

import com.example.rewards.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService service;

    public RewardController(RewardService service) {
        this.service = service;
    }

    /**
     * Gets the total and monthly reward points for a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a JSON object containing totalRewards and monthlyRewards map
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Map<String, Object>> getRewards(@PathVariable String customerId) {
        Map<String, Object> response = new HashMap<>();
        response.put("monthlyRewards", service.getMonthlyRewards(customerId));
        response.put("totalRewards", service.getTotalRewards(customerId));
        return ResponseEntity.ok(response);
    }
}
