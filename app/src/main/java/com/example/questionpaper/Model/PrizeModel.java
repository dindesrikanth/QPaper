package com.example.questionpaper.Model;


public class PrizeModel {
//    private String prize_id;
    private String range;
    private String percentage;

    public PrizeModel(String range, String percentage) {//String prize_id,
//        this.prize_id = prize_id;
        this.range = range;
        this.percentage = percentage;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
