package com.example.questionpaper.Model;

import java.util.List;

public class DashboardModelNew {
    private List<CourseSpecificTests> courseSpecificTests;

    public List<CourseSpecificTests> getCourseSpecificTests() {
        return courseSpecificTests;
    }
    public void setCourseSpecificTests(List<CourseSpecificTests> data) {
        this.courseSpecificTests = data;
    }


    public class CourseSpecificTests{
        private String courseName;
        private List<DashBoardTests> dashBoardTests;

        public String getCourseName() {
            return courseName;
        }
        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public List<DashBoardTests> getDashBoardTests() {
            return dashBoardTests;
        }
        public void setDashBoardTests(List<DashBoardTests> tests) {
            this.dashBoardTests = tests;
        }
    }

    public class DashBoardTests {
        private String testId;
        private String testName;
        private int totalQuestions;
        private int totalMarks;
        private int negativeMarks;
        private int testDuration;
        private String testDate;
        private String testTime;
        private String testFee;
        private String testLanguage;
        private int totalParticipants;
        private int firstPrize;
        private int totalPrize;
        private int prizeDistributionId;
        private int winPercentage;
        private String courseName;
        private boolean userEnroled;


        public String getTestId() {
            return testId;
        }

        public void setTestId(String testId) {
            this.testId = testId;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
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

        public int getTestDuration() {
            return testDuration;
        }

        public void setTestDuration(int testDuration) {
            this.testDuration = testDuration;
        }

        public String getTestDate() {
            return testDate;
        }

        public void setTestDate(String testDate) {
            this.testDate = testDate;
        }

        public String getTestTime() {
            return testTime;
        }

        public void setTestTime(String testTime) {
            this.testTime = testTime;
        }

        public String getTestFee() {
            return testFee;
        }

        public void setTestFee(String testFee) {
            this.testFee = testFee;
        }

        public String getTestLanguage() {
            return testLanguage;
        }

        public void setTestLanguage(String testLanguage) {
            this.testLanguage = testLanguage;
        }

        public int getTotalParticipants() {
            return totalParticipants;
        }

        public void setTotalParticipants(int totalParticipants) {
            this.totalParticipants = totalParticipants;
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

        public int getPrizeDistributionId() {
            return prizeDistributionId;
        }

        public void setPrizeDistributionId(int prizeDistributionId) {
            this.prizeDistributionId = prizeDistributionId;
        }

        public int getWinPercentage() {
            return winPercentage;
        }

        public void setWinPercentage(int winPercentage) {
            this.winPercentage = winPercentage;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public boolean getUserEnroled() {
            return userEnroled;
        }
        public void setUserEnroled(boolean userEnroled) {
            this.userEnroled = userEnroled;
        }
    }

}
