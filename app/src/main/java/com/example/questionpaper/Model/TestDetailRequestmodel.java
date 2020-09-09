package com.example.questionpaper.Model;

public class TestDetailRequestmodel {
    private int testId;
    private int prizeDistributionId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPrizeDistributionId() {
        return prizeDistributionId;
    }

    public void setPrizeDistributionId(int prizeDistributionId) {
        this.prizeDistributionId = prizeDistributionId;
    }

    public TestDetailRequestmodel(int testId, int prizeDistributionId) {
        this.testId = testId;
        this.prizeDistributionId = prizeDistributionId;
    }
}
