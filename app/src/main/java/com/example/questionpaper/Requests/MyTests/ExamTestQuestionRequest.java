package com.example.questionpaper.Requests.MyTests;

public class ExamTestQuestionRequest {
    String testId;
    String userId;
    public ExamTestQuestionRequest(String testId, String userId) {
        this.testId = testId;
        this.userId = userId;
    }
}
