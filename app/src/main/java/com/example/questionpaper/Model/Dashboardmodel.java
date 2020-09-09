package com.example.questionpaper.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Dashboardmodel {
    private List<Coursedata> data;

    public List<Coursedata> getData() {
        return data;
    }

    public void setData(List<Coursedata> data) {
        this.data = data;
    }

    public class Coursedata{
        private String courseName;
        private List<Testdata> tests;

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public List<Testdata> getTests() {
            return tests;
        }

        public void setTests(List<Testdata> tests) {
            this.tests = tests;
        }
    }

    public class Testdata {
        private String id;
        private String name;
        private String description;
        private String typeCode;
        private int totalQuestions;
        private int totalMarks;
        private int negativeMarks;
        private String date;
        private int duration;
        private String fee;
        private int numberOfParticipants;
        private String subjectName;
        private String courseName;
        private String testTime;
        private String testMedium;
        private int winPercentage;
        private int firstPrize;
        private int totalPrize;
        private int prizeAmount;
        private int prizeDistributionId;
        private String timeLeft;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getTimeLeft() {
            return timeLeft;
        }

        public void setTimeLeft(String timeLeft) {
            this.timeLeft = timeLeft;
        }
    }
}
