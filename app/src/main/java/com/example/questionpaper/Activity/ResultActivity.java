package com.example.questionpaper.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.questionpaper.Activity.TestSubmissionDialog.MyDialogListener;
import com.example.questionpaper.Adapter.TestAdapter;
import com.example.questionpaper.Fragments.StatusView;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.example.questionpaper.Service.StickyService;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();
        Intent intent = new Intent(ResultActivity.this, StickyService.class);
        stopService(intent);
    }

    private void initViews(){

    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {

                default:
                    break;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

