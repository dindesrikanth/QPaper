package com.example.questionpaper.Response.Payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class TopTestRanks  implements Comparator<TopTestRanks> {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("testId")
    @Expose
    private String testId;
    @SerializedName("testName")
    @Expose
    private String testName;
    @SerializedName("courseId")
    @Expose
    private String courseId;
    @SerializedName("courceName")
    @Expose
    private String courseName;

    public String getName() {
        return name;
    }
    public int getRank() {
        return rank;
    }
    public String getTestId() {
        return testId;
    }
    public String getTestName() {
        return testName;
    }
    public String getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }


    @Override
    public int compare(TopTestRanks t1, TopTestRanks t2) {
        int result =  t1.getRank() <  t2.getRank() ? -1 : t1.getRank() == t2.getRank() ? 0 : 1;
        return result;
    }
}
