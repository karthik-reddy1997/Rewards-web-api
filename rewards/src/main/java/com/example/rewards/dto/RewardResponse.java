package com.example.rewards.dto;

import java.util.List;
public class RewardResponse {

    private String customerId;
    private int totalPoints;
    private List<MonthlyReward> monthlyRewards;

    public RewardResponse() {
    }

    public RewardResponse(String customerId, int totalPoints, List<MonthlyReward> monthlyRewards) {
        this.customerId = customerId;
        this.totalPoints = totalPoints;
        this.monthlyRewards = monthlyRewards;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<MonthlyReward> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(List<MonthlyReward> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }
}
