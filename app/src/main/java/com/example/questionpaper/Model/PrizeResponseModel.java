package com.example.questionpaper.Model;


import java.util.List;

public class PrizeResponseModel {
    private int registeredUserCount;
    private List<PrizeModel> distributions;

    public int getRegisteredUserCount() {
        return registeredUserCount;
    }

    public void setRegisteredUserCount(int registeredUserCount) {
        this.registeredUserCount = registeredUserCount;
    }

    public List<PrizeModel> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<PrizeModel> distributions) {
        this.distributions = distributions;
    }
}
