package com.example.questionpaper.Response.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoursesListScreenResponse {
    @SerializedName("coursesDetails")
    @Expose
    private List<CoursesDetails> coursesDetails;

    public List<CoursesDetails> getCoursesDetails() {
        return coursesDetails;
    }
}
