package com.example.questionpaper.Screens.mytest;

import com.example.questionpaper.Response.mytests.LiveTest.TestData;

import java.util.List;

public interface RootViewClickInterface {
    void onRootViewClicked(int position, List<TestData> responseActualData);
}
