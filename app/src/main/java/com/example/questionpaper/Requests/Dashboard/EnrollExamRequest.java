package com.example.questionpaper.Requests.Dashboard;

public class EnrollExamRequest {
    int ammountPaid,testId,userId;
    String transactionInd,transactionType;


    public EnrollExamRequest(int ammountPaid,int testId,int userId,String transactionInd,String transactionType) {
        this.ammountPaid = ammountPaid;
        this.testId = testId;
        this.userId = userId;
        this.transactionInd = transactionInd;
        this.transactionType = transactionType;
    }

}
