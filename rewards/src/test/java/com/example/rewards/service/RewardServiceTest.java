package com.example.rewards.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link RewardService#calculatePoints(double)} using parameterized inputs.
 */
public class RewardServiceTest {

    private final RewardService service = new RewardService(null); // repo not needed for this test

    /**
     * Tests the reward point calculation logic using various transaction amounts.
     *
     * @param amount the transaction amount
     * @param expectedPoints the expected reward points
     */
    @ParameterizedTest(name = "Transaction amount {0} should return {1} reward points")
    @CsvSource({
            "120, 90",   // 2*20 + 50
            "100, 50",   // 1*50
            "75, 25",    // 1*25
            "200, 250",  // 2*100 + 50
            "50, 0",     // No rewards
            "30, 0"      // Below threshold
    })
    public void testCalculatePoints(double amount, int expectedPoints) {
        assertEquals(expectedPoints, service.calculatePoints(amount));
    }
}
