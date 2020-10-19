package com.example.questionpaper.Requests.Courses;

public class CoursesListSubmitRequest  {
    String preferredCourses;
    String userId;

    public CoursesListSubmitRequest(String preferredCourses,String userId) {
        this.preferredCourses = preferredCourses;
        this.userId = userId;
    }
}
