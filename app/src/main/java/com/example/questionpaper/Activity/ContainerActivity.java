package com.example.questionpaper.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.questionpaper.R;
import com.example.questionpaper.Screens.mytest.MyTestsLandingFragment;

public class ContainerActivity extends AppCompatActivity {
    FragmentManager fManager;
    FragmentTransaction tr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_activity);
        fManager= getSupportFragmentManager();
        displayFragment(0);
    }
    public void displayFragment(int position){
        switch (position){
            case 0:

                tr=fManager.beginTransaction();
                tr.replace(R.id.containerLayout,new MyTestsLandingFragment());
                tr.commit();
                break;

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                break;
        }
    }



}
