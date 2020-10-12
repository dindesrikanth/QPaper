package com.example.questionpaper.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Service.StickyService;
import com.example.questionpaper.ServiceCallbacks;
import com.google.gson.Gson;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity implements TestAdapter.OnItemClickListener, View.OnClickListener, StatusView.FragmentAListener, ServiceCallbacks {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private TestAdapter testAdapter;
    private TextView test_duration_counter;
    private Button mark_next;
    private Button clear_response;
    private Button save_next;
    private Button submit;
    private ArrayList<Questionesmodel> userList = null;
    private long durationInMinutes = 50;
    private long totalDurationInMillis = durationInMinutes * 60 * 1000;
    private final Handler handler = new Handler();
    private CountDownTimer countDownTimer;
    private long timeSpent = 0;
    private long remainingTime = 0;
    private boolean mIsRunning;
    private TestSubmissionDialog testSubmissionDialog;
    private TestSubmittedDialog testSubmittedDialog;
    private SnapHelper mSnapHelper;
    private boolean doubleBackToExitPressedOnce;
    public static final String BUNDLE_LIST_KEY = "BUNDLE_LIST_KEY";
    public static final String BUNDLE_TIME_KEY = "BUNDLE_TIME_KEY";
    private RelativeLayout test_main_layout;
    private ProgressBar test_loader;
    private String questionJsonString = "";
    private StickyService myService;
    private boolean bound = false;
    Intent stickyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        stickyService = new Intent(TestActivity.this, StickyService.class);
        startService(stickyService);
        initViews(getIntent().getExtras());
    }




    @Override
    protected void onStart() {
        super.onStart();
        bindService(stickyService, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // cast the IBinder and get MyService instance
            StickyService.LocalBinder binder = (StickyService.LocalBinder) service;
            myService = binder.getService();
            bound = true;
            myService.setCallbacks(TestActivity.this); // register
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    private void initViews(Bundle savedInstanceState){
        mark_next = findViewById(R.id.mark_next);
        clear_response = findViewById(R.id.clear_response);
        save_next = findViewById(R.id.save_next);
        submit = findViewById(R.id.submit);
        test_main_layout = findViewById(R.id.test_main_layout);
        test_loader = findViewById(R.id.test_loader);
        test_main_layout.setVisibility(View.GONE);
        test_loader.setVisibility(View.VISIBLE);
        mark_next.setOnClickListener(this);
        submit.setOnClickListener(this);
        test_duration_counter = findViewById(R.id.test_duration_counter);
        clear_response.setOnClickListener(this);
        save_next.setOnClickListener(this);
        if(savedInstanceState != null){
            userList = savedInstanceState.getParcelableArrayList(BUNDLE_LIST_KEY);
            long remainingTime = savedInstanceState.getLong(BUNDLE_TIME_KEY);
            if(userList != null && userList.size() > 0 && remainingTime > 0) {
                getUserList(true);
                initiateTimer(remainingTime);
                test_main_layout.setVisibility(View.VISIBLE);
                test_loader.setVisibility(View.GONE);
                Toast.makeText(this, getString(R.string.test_force_close_alert), Toast.LENGTH_LONG).show();
            }else{
                getQuestionsFromApi();
            }
        }else{
            getQuestionsFromApi();
        }
    }

    private void initiateTimer(long totalTime){
        test_duration_counter.setText(convertToHHMMSS(totalTime));
        countDownTimer = new CountDownTimer(totalTime, 1000) {

            public void onTick(long millisUntilFinished) {
                String hms = convertToHHMMSS(millisUntilFinished);
                test_duration_counter.setText(hms);
                timeSpent = totalDurationInMillis - millisUntilFinished;
                remainingTime = millisUntilFinished;
                if(testSubmissionDialog != null && testSubmissionDialog.isShowing()){
                    testSubmissionDialog.setTimerCount(hms);
                }
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                test_duration_counter.setText("00:00:00");
                timeUpAlert();
            }

        }.start();
    }

    private String convertToHHMMSS(long millisUntilFinished){
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
    }

    private void timeUpAlert(){
        showTestSubmittedAlert();
    }

    private void showTestSubmissionAlert(){
        testSubmissionDialog =  new TestSubmissionDialog(TestActivity.this);
        testSubmissionDialog.setData(userList, test_duration_counter.getText().toString());
        testSubmissionDialog.setCancelable(false);
        testSubmissionDialog.setDialogListener(new MyDialogListener()
        {

            @Override
            public void yesClicked() {
                    showTestSubmittedAlert();
            }

            @Override
            public void noClicked() {

            }
        });
        testSubmissionDialog.show();
    }

    private void showTestSubmittedAlert(){
        createResult();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
        testSubmittedDialog =  new TestSubmittedDialog(TestActivity.this);
        testSubmittedDialog.setData(userList, test_duration_counter.getText().toString());
        testSubmittedDialog.setCancelable(false);

        testSubmittedDialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testSubmittedDialog.cancel();
                if (bound) {
//            myService.setBundle(getLatestTestData());
                    myService.setCallbacks(null); // unregister
                    try {
                        unbindService(serviceConnection);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    bound = false;
                }
                finish();
                Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }


    public void completedAlert(){

        if(mIsRunning) {
            AlertDialog alertDialog = new AlertDialog.Builder(TestActivity.this).create();
            alertDialog.setTitle(getString(R.string.test_alert_title));
            alertDialog.setMessage(getString(R.string.test_alert_submit) + "--> Response --> " + createResult());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                            Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                            startActivity(intent);
                        }
                    });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }

    private String createResult(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("test_id", userList.get(0).getTest_id());
            jsonObject.put("user_id", "userId");
            jsonObject.put("sub_id", userList.get(0).getSub_id());
            jsonObject.put("course_id", userList.get(0).getCourse_id());
            jsonObject.put("time_spent", timeSpent);
            jsonObject.put("user_resp", createAnswers());
            submitAnswersToApi();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.e("JSON STRING --> " , jsonObject.toString());
        return jsonObject.toString();
    }
    private String createAnswers(){
        String answerString = "[";
        for(int i = 0; i < userList.size(); i++){
            if(i != 0){
                answerString += ",";
            }
            answerString += " \" " + (i + 1)  + ":" + convertAnswerIdToOptions(userList.get(i).getAnswerId()) + " \" ";
//            answerString += "," + convertAnswerIdToOptions(userList.get(i).getAnswerId());
        }
        answerString += "]";
        return answerString;
    }


    public  void submitAnswersToApi(){
        final AnswerSubmitModel answerSubmitModel = new AnswerSubmitModel("1", userList.get(0).getCourse_id() + "", userList.get(0).getSub_id()+ "", userList.get(0).getTest_id()+ "", createAnswers(), timeSpent + "");
        Call<AnswerSubmitModel> call = RetrofitClient.getInstance().getApi().submitAnswers(answerSubmitModel);
        call.enqueue(new Callback<AnswerSubmitModel>() {
            @Override
            public void onResponse(Call<AnswerSubmitModel> call, Response<AnswerSubmitModel> response) {

            }

            @Override
            public void onFailure(Call<AnswerSubmitModel> call, Throwable t) {

            }
        });
    }

    private String convertAnswerIdToOptions(String answerId){
        switch (answerId.trim()){
            case "1":
                return "A";
            case "2":
                return "B";
            case "3":
                return "C";
            case "4":
                return "D";
            case "5":
                return "E";
            case "":
                return "U";
        }
        return "U";
    }

    private void getUserList(boolean isTestResumed) {
        if(!isTestResumed) {
            try {
                JSONArray m_jArry = new JSONArray(questionJsonString);//loadJSONFromAsset()//questionJsonString
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
                            jo_inside.getString("opte"), "", false, false);
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

        }


        try {
            layoutManager = new LinearLayoutManager(TestActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView = findViewById(R.id.grid_recyclerView);
            mSnapHelper = new PagerSnapHelper();
            mSnapHelper.attachToRecyclerView(recyclerView);
            testAdapter = new TestAdapter(userList, getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            testAdapter.setOnItemClickListener(TestActivity.this);
            recyclerView.setAdapter(testAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
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

    private void getQuestionsFromApi(){
        Call<Object> call = RetrofitClient.getInstance().getApi().getAllQuesDetails();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Questionesmodel> list = (List<Questionesmodel>)response.body();
                        Gson gson = new Gson();
                        questionJsonString = gson.toJson(list);
                        test_main_layout.setVisibility(View.VISIBLE);
                        test_loader.setVisibility(View.GONE);
                        getUserList(false);
                        initiateTimer(totalDurationInMillis);
                    }else{
                        showMessageAndCloseScreen();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                showMessageAndCloseScreen();
                return;
            }
        });

    }

    private void showMessageAndCloseScreen(){
        Toast.makeText(TestActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    @Override
    public void optionsclick(int checkid, int position) {
      if(userList != null){
          userList.get(position).setAnswerId(checkid + "");
          testAdapter.notifyDataSetChanged();
      }
    }

    @Override
    public void onPageSeen(final int position) {
        if(userList != null){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    View view = mSnapHelper.findSnapView(layoutManager);
                    int pos = recyclerView.getChildAdapterPosition(view);
//                    if(position == layoutManager.findFirstCompletelyVisibleItemPosition()) {
                        userList.get(pos).setSeen(true);
//                    }
                }
            }, 100);

        }
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.mark_next:
                    userList.get(layoutManager.findFirstCompletelyVisibleItemPosition()).setMarked(true);
                    testAdapter.notifyDataSetChanged();
                    layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                    break;

                case R.id.clear_response:
                    userList.get(layoutManager.findFirstCompletelyVisibleItemPosition()).setAnswerId("");
                    testAdapter.notifyDataSetChanged();
                    break;

                case R.id.save_next:
                    userList.get(layoutManager.findFirstCompletelyVisibleItemPosition()).setMarked(false);
                    layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition() + 1);
                    testAdapter.notifyDataSetChanged();
                    break;

                case R.id.submit:
                    showTestSubmissionAlert();
                    break;

                default:
                    break;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openStatusView(View v) {
        StatusView statusView = new StatusView();
        statusView.setData(userList, TestActivity.this,layoutManager.findFirstCompletelyVisibleItemPosition() );
//        FragmentTransaction fragmenttras = getSupportFragmentManager().beginTransaction();
//        fragmenttras.add(R.id.fragment_container, statusView)
//                .commit();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, statusView);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
//        isListFragmentShown = true;
    }

    @Override
    public void onInputASent(int input) {
        layoutManager.scrollToPosition(input);
        FragmentManager fm = this.getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }
//        isListFragmentShown = false;
    }

    @Override
    public void submitTest() {
        showTestSubmissionAlert();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsRunning = true;
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getSupportFragmentManager();
        if(fm.getBackStackEntryCount()>0) {
            fm.popBackStack();
        }else {
            if (doubleBackToExitPressedOnce) {
                minimizeApp();
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    public Bundle getLatestTestData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_LIST_KEY,userList);
        bundle.putLong(BUNDLE_TIME_KEY, remainingTime);
        if (bound) {
//            myService.setBundle(getLatestTestData());
            myService.setCallbacks(null); // unregister
            try {
                unbindService(serviceConnection);
            }catch (Exception e){
                e.printStackTrace();
            }
            bound = false;
        }
        return bundle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }

    }
}

