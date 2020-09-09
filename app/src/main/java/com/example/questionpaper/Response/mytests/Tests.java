package com.example.questionpaper.Response.mytests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tests {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("typeCode")
    @Expose
    String typeCode;
    @SerializedName("totalQuestions")
    @Expose
    int totalQuestions;
    @SerializedName("totalMarks")
    @Expose
    int totalMarks;
    @SerializedName("negativeMarks")
    @Expose
    int negativeMarks;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("duration")
    @Expose
    int duration;
    @SerializedName("fee")
    @Expose
    String fee;
    @SerializedName("numberOfParticipants")
    @Expose
    int numberOfParticipants;
    @SerializedName("subjectName")
    @Expose
    String subjectName;
    @SerializedName("courseName")
    @Expose
    String courseName;
    @SerializedName("testTime")
    @Expose
    String testTime;
    @SerializedName("testMedium")
    @Expose
    String testMedium;
    @SerializedName("winPercentage")
    @Expose
    int winPercentage;
    @SerializedName("firstPrize")
    @Expose
    int firstPrize;
    @SerializedName("totalPrize")
    @Expose
    int totalPrize;
    @SerializedName("prizeAmount")
    @Expose
    int prizeAmount;
    @SerializedName("prizeDistributionId")
    @Expose
    int prizeDistributionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getNegativeMarks() {
        return negativeMarks;
    }

    public void setNegativeMarks(int negativeMarks) {
        this.negativeMarks = negativeMarks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestMedium() {
        return testMedium;
    }

    public void setTestMedium(String testMedium) {
        this.testMedium = testMedium;
    }

    public int getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(int winPercentage) {
        this.winPercentage = winPercentage;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public void setFirstPrize(int firstPrize) {
        this.firstPrize = firstPrize;
    }

    public int getTotalPrize() {
        return totalPrize;
    }

    public void setTotalPrize(int totalPrize) {
        this.totalPrize = totalPrize;
    }

    public int getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(int prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public int getPrizeDistributionId() {
        return prizeDistributionId;
    }

    public void setPrizeDistributionId(int prizeDistributionId) {
        this.prizeDistributionId = prizeDistributionId;
    }



}
