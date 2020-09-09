package com.example.questionpaper.Activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Model.Questionexplanation;
import com.example.questionpaper.R;

import java.util.Random;

public class Solutions extends AppCompatActivity {

    private TextView question,explanation,quesno;
    private Button next;
    int quescount =1;
    RadioButton r1,r2,r3,r4;
    RadioGroup RG;
    private Questionexplanation mQuestions = new Questionexplanation();
    private String manswer;
    private String manswerexp;
    private int mQuestionlenth = mQuestions.mQuestion.length;
    Random r;
    String quennum ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        r = new Random();
        r1 = findViewById(R.id.option1);
        r2 = findViewById(R.id.option2);
        r3 = findViewById(R.id.option3);
        r4 = findViewById(R.id.option4);
        question = findViewById(R.id.question);
        quesno = findViewById(R.id.questionno);
        explanation = findViewById(R.id.explanationtext);
        updateQuestion(r.nextInt(mQuestionlenth));
       // explanation.setText(manswerexp);

    }
    public  void next(View V)
    {
        quescount++;
        updateQuestion(r.nextInt(mQuestionlenth));
       /* r1.setBackgroundColor(0x00000000);
        r2.setBackgroundColor(0x00000000);
        r3.setBackgroundColor(0x00000000);
        r4.setBackgroundColor(0x00000000);*/
        explanation.setText(manswerexp);
        //quesno.setText("" + quescount);


    }
    private  void updateQuestion(int num){

        question.setText(quescount+" "+"."+" "+mQuestions.getQuestion(num));
        quesno.setText(""+quescount);
        quennum = quesno.getText().toString();
        r1.setText(mQuestions.getChoice1(num));
        r2.setText(mQuestions.getChoice2(num));
        r3.setText(mQuestions.getChoice3(num));
        r4.setText(mQuestions.getChoice4(num));
        manswer= mQuestions.getcorrectans(num);
        explanation.setText(mQuestions.getcorrectansexp(num));


    }
}
