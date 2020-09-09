package com.example.questionpaper.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.questionpaper.Adapter.Recycler_view_adapter;
import com.example.questionpaper.Fragments.listview;
import com.example.questionpaper.Model.Question;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.Model.user_response;
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
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.questionpaper.Fragments.listview.Markattempt;

public class MainActivity extends AppCompatActivity implements Recycler_view_adapter.OnItemClickListener, listview.FragmentAListener {
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    Recycler_view_adapter recyclerViewAdapter;
    public static List<Questionesmodel> userList = null;
    private static CountDownTimer coutdowntime;
    private long backpreesedtime;
    private Toast backToast;
    TextView questiones, getscore, quesno, userrespose, respose, resp, sucess, optionsget;
    ScrollView scrollview;
    private static TextView timer;
    Button next, mark;
    private Question mQuestions = new Question();
    final String KEY_SAVED_Question = "SAVE_NUMBER";
    Random r;

    private boolean mTimerRunning;
    private long mStartTimeInMillis;
    private long mEndTime;
    private static final long START_TIME_IN_MILLIS = 30 * 60 * 1000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private int mscore = 0;
    private String quennow;
    Questionesmodel updatedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getUserList();
        if (coutdowntime == null) {
            startTimer();
        }
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler);
        questiones = findViewById(R.id.questionmain);
        getscore = findViewById(R.id.score);
        quesno = findViewById(R.id.questionno);
        userrespose = findViewById(R.id.userrespose);
        optionsget = findViewById(R.id.options);
        respose = findViewById(R.id.respose);
        timer = findViewById(R.id.gettimer);
        sucess = findViewById(R.id.sucess);
        getscore.setText("score:" + mscore);
        next = findViewById(R.id.save);
        scrollview = findViewById(R.id.nestscroll);
        mark = findViewById(R.id.mark);
        optionsget.setText("U");
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            FragmentTransaction fragmenttras = getSupportFragmentManager().beginTransaction();
            fragmenttras.remove(fragment);
            fragmenttras.commit();
        } else {
            if (backpreesedtime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();
                moveTaskToBack(true);
                return;
            } else {
                backToast = Toast.makeText(getBaseContext(), "Press back agin to exit", Toast.LENGTH_SHORT);
                backToast.show();

            }
            backpreesedtime = System.currentTimeMillis();

        }
    }

    private void startTimer() {
        coutdowntime = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;

            }
        }.start();

        mTimerRunning = true;

    }


    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d:%02d", hours, minutes, seconds);
        }

        timer.setText(timeLeftFormatted);
    }

    private void pauseTimer() {
        coutdowntime.cancel();
        mTimerRunning = false;

    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();

    }

    public void submit(View v) {
        pauseTimer();
        passrespn();
    }

    public void passrespn() {
        String ressub = resp.getText().toString();
        final user_response Response = new user_response(1234, 12345, 1, 1, ressub, 30, null, "suhana", null, "hasnu");
        Call<user_response> call = RetrofitClient.getInstance().getApi().userLogins(Response);
        call.enqueue(new Callback<user_response>() {
            @Override
            public void onResponse(Call<user_response> call, Response<user_response> response) {
                if (!response.isSuccessful()) {
                    sucess.append("Thanks for your regstration!" + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<user_response> call, Throwable t) {
                sucess.setText(t.getMessage());
            }
        });
    }

    public void listofquen(View v) {
//        recyclerView.scrollToPosition(Integer.parseInt(quennow));
        Markattempt(userList);
        FragmentTransaction fragmenttras = getSupportFragmentManager().beginTransaction();
        fragmenttras.add(R.id.fragment_container, new listview())
                //.addToBackStack("fragment_1")
                .commit();
        Bundle bundle = new Bundle();
        bundle.putString("edttext", String.valueOf(userList));
    }

    private void stopCountdown() {
        if (coutdowntime != null) {
            coutdowntime.cancel();
            coutdowntime = null;
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("jsonresponse.txt");
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

    private void gameover() {
        AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(MainActivity.this);
        alertdialogbuilder
                .setMessage("Are you sure,You want to submit?")
                .setCancelable(false)
                .setPositiveButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //getscore.setVisibility(View.VISIBLE);
                                startTimer();
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                stopCountdown();
                                Intent scoreact = new Intent(MainActivity.this, score.class);
                                scoreact.putExtra("myscore", mscore);
                                scoreact.putExtra("time", timer.getText().toString());
                                startActivity(scoreact);
                                finish();
                            }
                        });
        AlertDialog alertDialog = alertdialogbuilder.create();
        alertDialog.show();
    }


    private void getUserList() {
        try {
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
            userList = new ArrayList<>();
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Questionesmodel questionesmodel = new Questionesmodel(jo_inside.getLong("course_id"),
                        jo_inside.getLong("sub_id"),
                        jo_inside.getLong("test_id"),
                        jo_inside.getLong("ques_id"),
                        jo_inside.getLong("ques_local_id"),
                        jo_inside.getString("ques_detail"),
                        jo_inside.getString("opta"),
                        jo_inside.getString("optb"),
                        jo_inside.getString("optc"),
                        jo_inside.getString("optd"),
                        jo_inside.getString("opte"), null, false, false);
                userList.add(questionesmodel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Collections.sort(userList, new Comparator<Questionesmodel>() {
            @Override
            public int compare(Questionesmodel o1, Questionesmodel o2) {
                return (int) (o1.getQues_local_id() - o2.getQues_local_id());
            }
        });



       /* Call<List<Questionesmodel>> call = RetrofitClient.getInstance().getApi().getAllQuesDetails();
        call.enqueue(new Callback<List<Questionesmodel>>() {
            @Override
            public void onResponse(Call<List<Questionesmodel>> call, Response<List<Questionesmodel>> response) {

                try {
                    userList = response.body();
                    List<Questionesmodel> list = response.body();*/
        try {
            layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            SnapHelper snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
            recyclerViewAdapter = new Recycler_view_adapter(getApplicationContext(), userList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerViewAdapter.setOnItemClickListener(MainActivity.this);
            recyclerView.setAdapter(recyclerViewAdapter);
            scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    int position = recyclerViewAdapter.getPosition();
                    Questionesmodel updatedItem = userList.get(position);
//                    if (updatedItem.getSelectedOption() == 0) {
//                       updatedItem.setQues_Status(1);
//
//                    }
//                    else {
//                        updatedItem.setQues_Status(2);
//                    }
                    userList.set(position, updatedItem);
                   recyclerViewAdapter.updateList(userList);
                }

            });
            mark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = recyclerViewAdapter.getPosition();
                    Questionesmodel updatedItem = userList.get(position);
//                    if (updatedItem.getSelectedOption() == 0) {
//                        updatedItem.setQues_Status(3);
//
//                    }
//                    else {
//                        updatedItem.setQues_Status(4);
//                    }
                    userList.set(position, updatedItem);
                    recyclerViewAdapter.updateList(userList);
                    recyclerView.getLayoutManager().scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);
                    sucess.setText("sucesstext");
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = recyclerViewAdapter.getPosition();
                    Questionesmodel updatedItem = userList.get(position);
//                    if (updatedItem.getSelectedOption() ==0) {
//                        updatedItem.setQues_Status(1);
//                    } else {
//                        updatedItem.setQues_Status(2);
//                    }
                    userList.set(position, updatedItem);
                    recyclerViewAdapter.updateList(userList);
                    recyclerView.getLayoutManager().scrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);
                }
            });
        } catch (Exception ex) {
        }
    }
    @Override
    public void optionsclick(int checkid, int position) {
        Questionesmodel updatedItem = userList.get(position);
//        updatedItem.setSelectedOption(checkid);
//        updatedItem.setQues_Status(2);
        userList.set(position, updatedItem);
        recyclerViewAdapter.updateList(userList);
    }

    @Override
    public void onInputASent(int input) {

    }
    //redirect to position
    public void gotoQuestion(String pos) {
        quennow = pos;
        Log.d("recyclerView.",""+quennow);
    }
    //@Override
   /* public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            recyclerView.scrollToPosition(Integer.parseInt(quennow));
            FragmentTransaction fragmenttras = getSupportFragmentManager().beginTransaction();
            fragmenttras.remove(fragment);
            fragmenttras.commit();
        } else if (backpreesedtime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back agin to exit", Toast.LENGTH_SHORT);
            backToast.show();
            backpreesedtime = System.currentTimeMillis();
        }
    }*/
}

