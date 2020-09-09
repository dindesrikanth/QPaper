package com.example.questionpaper.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Adapter.Score_Recyclerviewadapter;
import com.example.questionpaper.Fragments.RecylerViewAdapter;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.Model.ScoreModel;
import com.example.questionpaper.Network.RetrofitClient;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class score extends AppCompatActivity {
    RecyclerView score_recyclerview;
    TextView error;
    Score_Recyclerviewadapter score_recyclerViewAdapter;
    private LinearLayoutManager layoutManager;
    ScoreModel scoreModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
score_recyclerview = findViewById(R.id.score_recyclerview);
         error =findViewById(R.id.error);
        //TextView scoreTextView = (TextView) findViewById(R.id.getscore);
       // TextView timespent = (TextView) findViewById(R.id.getTimespent);
       // int intValue = getIntent().getIntExtra("myscore", 0);
        //String output = getIntent().getExtras().getString("time");
       // String time = getIntent().getStringExtra("mytime");
        //scoreTextView.setText(""+intValue);

        //Bundle bundle = getIntent().getExtras();
       // String message = bundle.getString("mytime");
        //timespent.setText(output);
        apiforuserresult();
    }
    public  void soutions(View v)
    {
        Intent soutionscr = new Intent(this, Solutions.class);
        startActivity(soutionscr);
    }
    public  void apiforuserresult(){
       Call<ScoreModel> call = RetrofitClient.getInstance().getApi().getUserResult();
      call.enqueue(new Callback<ScoreModel>() {
            @Override
           public void onResponse(Call<ScoreModel> call, Response<ScoreModel> response) {
              /* try {
                    JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
                    scoreModelList = new ArrayList<>();
                    for (int i = 0; i < m_jArry.length(); i++) {
                        JSONObject jo_inside = m_jArry.getJSONObject(i);
                        ScoreModel questionesmodel = new ScoreModel(jo_inside.getLong("test_id"),
                                jo_inside.getLong("user_id"),
                                jo_inside.getLong("time_spent"),
                                jo_inside.getLong("achieved_marks"),
                                jo_inside.getLong("ques_right"),
                                jo_inside.getLong("ques_wrong"),
                                jo_inside.getLong("ques_unattempted"));
                        scoreModelList.add(questionesmodel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/



                try {
if(response.isSuccessful())
                    scoreModelList = response.body();
                    layoutManager = new LinearLayoutManager(score.this, LinearLayoutManager.HORIZONTAL, false);
                    score_recyclerViewAdapter = new Score_Recyclerviewadapter(scoreModelList,getApplicationContext());
                    score_recyclerview.setLayoutManager(layoutManager);
                    score_recyclerview.setAdapter(score_recyclerViewAdapter);
                } catch (Exception ec) {

                // error.setText(ec.getMessage());
                }
            }

            @Override
           public void onFailure(Call<ScoreModel> call, Throwable t) {
                error.setText(t.getMessage());
            }

      });
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("jsonresult.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
