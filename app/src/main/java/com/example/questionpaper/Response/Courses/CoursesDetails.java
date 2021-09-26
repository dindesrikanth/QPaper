package com.example.questionpaper.Response.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesDetails {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("selected")
    @Expose
    private boolean isSelected;


    //private boolean isSelected= false;

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }



}
