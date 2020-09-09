package com.example.questionpaper.Response.mytests.UpComing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tests {
    @SerializedName("id")
    @Expose
    private String id;
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
    private String totalQuestions;
    @SerializedName("totalMarks")
    @Expose
    private String totalMarks;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("negativeMarks")
    @Expose
    private String negativeMarks;
    @SerializedName("numberOfParticipants")
    @Expose
    private String numberOfParticipants;
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
    private String winPercentage;
    @SerializedName("firstPrize")
    @Expose
    private String firstPrize;
    @SerializedName("totalPrize")
    @Expose
    private String totalPrize;
    @SerializedName("prizeAmount")
    @Expose
    private String prizeAmount;
    @SerializedName("prizeDistributionId")
    @Expose
    private String prizeDistributionId;

    public String getId() {
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

    public String getTotalQuestions() {
        return totalQuestions;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getFee() {
        return fee;
    }

    public String getNegativeMarks() {
        return negativeMarks;
    }

    public String getNumberOfParticipants() {
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

    public String getWinPercentage() {
        return winPercentage;
    }

    public String getFirstPrize() {
        return firstPrize;
    }

    public String getTotalPrize() {
        return totalPrize;
    }

    public String getPrizeAmount() {
        return prizeAmount;
    }

    public String getPrizeDistributionId() {
        return prizeDistributionId;
    }
}
