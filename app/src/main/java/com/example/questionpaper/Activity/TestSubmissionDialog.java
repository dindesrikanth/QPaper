package com.example.questionpaper.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

public class TestSubmissionDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Dialog d;
    private Button yesButton, noButton;
    private MyDialogListener myDialogListener;
    private TextView time_remaining_value, attemptedCount_value, unattemptedCount_value, markedCount_value, message_layout;
    private List<Questionesmodel> item;
    String remainingTime;
    private LinearLayout evaluation_layout, footer_bar, parentLayout;
    final Handler handler = new Handler();

    public TestSubmissionDialog(@NonNull Context context) {
        super(context);
    }

    public void setData(List<Questionesmodel> item, String remainingTime){
        this.item = item;
        this.remainingTime = remainingTime;
    }
    public void setDialogListener(MyDialogListener myDialogListener) {
        this.myDialogListener = myDialogListener;
    }


    public void setTimerCount(String time){
            time_remaining_value.setText(time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_summary);
        yesButton = (Button) findViewById(R.id.yesButton);
        noButton = (Button) findViewById(R.id.noButton);
        time_remaining_value = (TextView) findViewById(R.id.time_remaining_value);
        attemptedCount_value = (TextView) findViewById(R.id.attemptedCount_value);
        unattemptedCount_value = (TextView) findViewById(R.id.unattemptedCount_value);
        markedCount_value = (TextView) findViewById(R.id.markedCount_value);


        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
        int attemptCount = 0,markCount = 0;
        for (int i=0; i<item.size(); i++) {
            if(item.get(i).isMarked()){
                markCount += 1;
            }

            if(!TextUtils.isEmpty(item.get(i).getAnswerId())){
                attemptCount += 1;
            }
        }
        int unattemptedCount = item.size() - attemptCount;

        time_remaining_value.setText(remainingTime);
        attemptedCount_value.setText(attemptCount + "");
        unattemptedCount_value.setText(unattemptedCount + "");
        markedCount_value.setText(markCount + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yesButton:

                myDialogListener.yesClicked();
                dismiss();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        parentLayout.setBackgroundColor(Color.WHITE);
//                        footer_bar.setVisibility(View.GONE);
//                        evaluation_layout.setVisibility(View.VISIBLE);
//                        message_layout.setVisibility(View.GONE);
//                        myDialogListener.yesClicked();
//                        dismiss();
//                    }
//                }, 3000);

                break;
            case R.id.noButton:
                myDialogListener.noClicked();
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public static interface MyDialogListener {
        public void yesClicked();

        public void noClicked();
    }
}