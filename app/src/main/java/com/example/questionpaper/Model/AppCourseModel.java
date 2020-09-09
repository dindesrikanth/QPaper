package com.example.questionpaper.Model;


public class AppCourseModel {
//    private String course_id;
    private String course_name;
    private boolean isSelected;

//    public String getCourse_id() {
//        return course_id;
//    }
//
//    public void setCourse_id(String course_id) {
//        this.course_id = course_id;
//    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public AppCourseModel( String course_name, boolean isSelected) {//String course_id,
//        this.course_id = course_id;
        this.course_name = course_name;
        this.isSelected = isSelected;
    }
}
