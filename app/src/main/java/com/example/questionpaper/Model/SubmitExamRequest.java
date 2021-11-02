package com.example.questionpaper.Model;

import java.util.List;

public class SubmitExamRequest {
    private String testId,timeSpent,userId;
    private List<QuestionResponse> questionResponse;

    public SubmitExamRequest(String testId, String timeSpent, String userId, List<QuestionResponse> questionResponse){
        this.testId= testId;
        this.timeSpent = timeSpent;
        this.userId =userId;
        this.questionResponse = questionResponse;
    }
}
