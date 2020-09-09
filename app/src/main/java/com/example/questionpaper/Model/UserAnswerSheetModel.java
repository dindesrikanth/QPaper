package com.example.questionpaper.Model;

import java.util.ArrayList;
import java.util.List;

public class UserAnswerSheetModel {

    public List<Questionesmodel> getAnswerSheetList() {
        return answerSheetList;
    }

    public void setAnswerSheetList(ArrayList<Questionesmodel> answerSheetList) {
        this.answerSheetList = answerSheetList;
    }

    private ArrayList<Questionesmodel> answerSheetList;
}
