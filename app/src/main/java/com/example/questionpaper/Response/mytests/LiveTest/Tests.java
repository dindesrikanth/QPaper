package com.example.questionpaper.Response.mytests.LiveTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tests {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("totalQuestions")
    @Expose
    private int totalQuestions;
    @SerializedName("totalMarks")
    @Expose
    private int totalMarks;
    @SerializedName("negativeMarks")
    @Expose
    private int negativeMarks;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("numberOfParticipants")
    @Expose
    private int numberOfParticipants;
    @SerializedName("subjectName")
    @Expose
    private String subjectName;
    @SerializedName("courseName")
    @Expose
    private String courseName;
    @SerializedName("testTime")
    @Expose
    private String testTime;
    @SerializedName("testMedium")
    @Expose
    private String testMedium;
    @SerializedName("winPercentage")
    @Expose
    private int winPercentage;
    @SerializedName("firstPrize")
    @Expose
    private int firstPrize;
    @SerializedName("totalPrize")
    @Expose
    private int totalPrize;
    @SerializedName("prizeAmount")
    @Expose
    private int prizeAmount;
    @SerializedName("prizeDistributionId")
    @Expose
    private int prizeDistributionId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public int getNegativeMarks() {
        return negativeMarks;
    }

    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public String getFee() {
        return fee;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTestTime() {
        return testTime;
    }

    public String getTestMedium() {
        return testMedium;
    }

    public int getWinPercentage() {
        return winPercentage;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public int getPrizeDistributionId() {
        return prizeDistributionId;
    }
}
