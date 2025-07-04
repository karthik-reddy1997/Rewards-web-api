package com.example.rewards.controller;

import com.example.rewards.controller.RewardController;
import com.example.rewards.entity.Transaction;
import com.example.rewards.repository.TransactionRepository;
import com.example.rewards.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup() {
        transactionRepository.deleteAll();

        transactionRepository.save(new Transaction("C001", 100.0, LocalDate.parse("2025-04-01")));
        transactionRepository.save(new Transaction("C001", 120.0, LocalDate.parse("2025-04-15")));
        transactionRepository.save(new Transaction("C001", 75.0, LocalDate.parse("2025-05-10")));
        transactionRepository.save(new Transaction("C001", 200.0, LocalDate.parse("2025-05-15")));
        transactionRepository.save(new Transaction("C001", 50.0, LocalDate.parse("2025-06-20")));
        transactionRepository.save(new Transaction("C002", 110.0, LocalDate.parse("2025-05-01")));
        transactionRepository.save(new Transaction("C002", 90.0, LocalDate.parse("2025-06-01")));
    }

    @Test
    public void testGetRewards() throws Exception {
        mockMvc.perform(get("/api/rewards/C001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalRewards").value(415))
                .andExpect(jsonPath("$.monthlyRewards.Apr").value(140))
                .andExpect(jsonPath("$.monthlyRewards.May").value(275))
                .andExpect(jsonPath("$.monthlyRewards.Jun").value(0));
    }
}
