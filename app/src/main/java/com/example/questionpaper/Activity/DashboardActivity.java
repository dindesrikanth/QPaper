package com.example.questionpaper.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.questionpaper.Adapter.CourseAdapter;
import com.example.questionpaper.Adapter.CourseItemAdapter;
import com.example.questionpaper.Adapter.TestAdapter;
import com.example.questionpaper.Fragments.StatusView;
import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.AppCourseModel;
import com.example.questionpaper.Model.Course;
import com.example.questionpaper.Model.Dashboardmodel;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Service.StickyService;
import com.example.questionpaper.ServiceCallbacks;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements  View.OnClickListener, CourseAdapter.OnItemClickListener, CourseItemAdapter.OnItemClickListener{

    private LinearLayoutManager layoutManager;
    private TextView btn_notifications, btn_home, btn_more, btn_test;
    private ImageView iv_user, iv_refresh;
    private RelativeLayout dashboard_main_layout, home_layout, test_layout, notification_layout, more_layout;
    private ProgressBar dashboard_loader;
    private RecyclerView rv_sub_header, rv_items;
    private static final int HOME_LAYOUT_ID = 1;
    private static final int TEST_LAYOUT_ID = 2;
    private static final int NOTIFICATIONS_LAYOUT_ID = 3;
    private static final int MORE_LAYOUT_ID = 4;
    int currentSelectedFooerId = -1;
    private List<Dashboardmodel.Coursedata> coursedataList;
    private List<Dashboardmodel.Testdata> testDataList;

    private SimpleDateFormat dateFormatInput, dateFormatOutput, timeFormatInput, timeFormatOutput, dateTimeFormat, outputDateTimeFormat;

    private CourseAdapter courseAdapter;
    private CourseItemAdapter courseItemAdapter;
    List<AppCourseModel> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
    }




    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    private void initViews(){
        dateFormatInput = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        timeFormatInput = new SimpleDateFormat("HH:mm:ss");
        timeFormatOutput = new SimpleDateFormat("hh:mm a");
        dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        outputDateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        outputDateTimeFormat.setTimeZone(TimeZone.getDefault());
        iv_user = findViewById(R.id.iv_user);
        iv_refresh = findViewById(R.id.iv_refresh);
        btn_notifications = findViewById(R.id.btn_notifications);
        btn_home = findViewById(R.id.btn_home);
        btn_more = findViewById(R.id.btn_more);
        btn_test = findViewById(R.id.btn_test);
        home_layout = findViewById(R.id.home_layout);
        test_layout = findViewById(R.id.test_layout);
        notification_layout = findViewById(R.id.notification_layout);
        more_layout = findViewById(R.id.more_layout);
        rv_sub_header = findViewById(R.id.rv_sub_header);
        rv_items = findViewById(R.id.rv_items);
        dashboard_main_layout = findViewById(R.id.dashboard_main_layout);
        dashboard_loader = findViewById(R.id.dashboard_loader);
        dashboard_main_layout.setVisibility(View.GONE);
        dashboard_loader.setVisibility(View.VISIBLE);
        iv_user.setOnClickListener(this);
        iv_refresh.setOnClickListener(this);
        btn_notifications.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_more.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        setFooter(HOME_LAYOUT_ID);
        getDashboardData();

    }

    private void setSubHeader(){
        try {
            layoutManager = new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rv_sub_header = findViewById(R.id.rv_sub_header);
            courseList = getCourseList();
            courseAdapter = new CourseAdapter(courseList, getApplicationContext());
            rv_sub_header.setLayoutManager(layoutManager);
            courseAdapter.setOnItemClickListener(DashboardActivity.this);
            rv_sub_header.setAdapter(courseAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setItems(){
        try {
                layoutManager = new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.VERTICAL, false);
            rv_items = findViewById(R.id.rv_items);
            testDataList = getTestDataList(coursedataList.get(0).getCourseName());
            courseItemAdapter = new CourseItemAdapter(testDataList, getApplicationContext());
            rv_items.setLayoutManager(layoutManager);
            courseItemAdapter.setOnItemClickListener(DashboardActivity.this);
            rv_items.setAdapter(courseItemAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void getDashboardData(){
        Call<Dashboardmodel> call = RetrofitClient.getInstance().getApi().getDashboardDetails();
        call.enqueue(new Callback<Dashboardmodel>() {
            @Override
            public void onResponse(Call<Dashboardmodel> call, Response<Dashboardmodel> response) {
                try {
                    if (response.isSuccessful()) {


//                        Gson gson = new Gson();
//                        String json = loadJSONFromAsset();
//                        Dashboardmodel dashboardmodel = gson.fromJson(json, Dashboardmodel.class);
//                        coursedataList = dashboardmodel.getData();


                        coursedataList = response.body().getData();
                        dashboard_main_layout.setVisibility(View.VISIBLE);
                        dashboard_loader.setVisibility(View.GONE);
                        setSubHeader();
                        setItems();
                    }else{
                        showMessageAndCloseScreen();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<Dashboardmodel> call, Throwable t) {
                showMessageAndCloseScreen();
                return;
            }
        });
    }

    private void showMessageAndCloseScreen(){
        Toast.makeText(DashboardActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.iv_user:
                    Toast.makeText(this, "User Clicked", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_notifications:
                    setFooter(NOTIFICATIONS_LAYOUT_ID);
                    break;

                case R.id.btn_test:
                    setFooter(TEST_LAYOUT_ID);
                    break;

                case R.id.btn_home:
                    setFooter(HOME_LAYOUT_ID);
                    break;

                case R.id.btn_more:
                    setFooter(MORE_LAYOUT_ID);
                    break;

                case R.id.iv_refresh:
                    getDashboardData();
                    break;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setFooter(int id){
        switch(currentSelectedFooerId){
            case HOME_LAYOUT_ID:
                btn_home.setTextColor(getColor(R.color.dashboard_grey));
                btn_home.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.icon_home_unselected) ,null,null);
                home_layout.setVisibility(View.GONE);
                break;

            case TEST_LAYOUT_ID:
                btn_test.setTextColor(getColor(R.color.dashboard_grey));
                btn_test.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.icon_degree_unselected) ,null,null);
                test_layout.setVisibility(View.GONE);
                break;

            case NOTIFICATIONS_LAYOUT_ID:
                btn_notifications.setTextColor(getColor(R.color.dashboard_grey));
                btn_notifications.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.icon_notification_bell_unselected) ,null,null);
                notification_layout.setVisibility(View.GONE);
                break;

            case MORE_LAYOUT_ID:
                btn_more.setTextColor(getColor(R.color.dashboard_grey));
                btn_more.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.icon_more_unselected) ,null,null);
                more_layout.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        switch(id){
            case HOME_LAYOUT_ID:
                btn_home.setTextColor(getColor(R.color.test_button_blue));
                Drawable drawableHome = getDrawable(R.drawable.icon_home_selected);
                drawableHome.setTint(getColor(R.color.test_button_blue));
                btn_home.setCompoundDrawablesWithIntrinsicBounds(null, drawableHome ,null,null);
                home_layout.setVisibility(View.VISIBLE);
                break;

            case TEST_LAYOUT_ID:
                btn_test.setTextColor(getColor(R.color.test_button_blue));
                Drawable drawableTest = getDrawable(R.drawable.icon_degree_selected);
                drawableTest.setTint(getColor(R.color.test_button_blue));
                btn_test.setCompoundDrawablesWithIntrinsicBounds(null, drawableTest ,null,null);
                test_layout.setVisibility(View.VISIBLE);
                break;

            case NOTIFICATIONS_LAYOUT_ID:
                btn_notifications.setTextColor(getColor(R.color.test_button_blue));
                Drawable drawableNotifications = getDrawable(R.drawable.icon_notification_bell_selected);
                drawableNotifications.setTint(getColor(R.color.test_button_blue));
                btn_notifications.setCompoundDrawablesWithIntrinsicBounds(null, drawableNotifications ,null,null);
                notification_layout.setVisibility(View.VISIBLE);
                break;

            case MORE_LAYOUT_ID:
                btn_more.setTextColor(getColor(R.color.test_button_blue));
                Drawable drawableMore = getDrawable(R.drawable.icon_more_selected);
                drawableMore.setTint(getColor(R.color.test_button_blue));
                btn_more.setCompoundDrawablesWithIntrinsicBounds(null, drawableMore ,null,null);
                more_layout.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
        currentSelectedFooerId = id;
    }

    private List<AppCourseModel> getCourseList(){
        List<AppCourseModel> courseList = new ArrayList<>();
        for(int i = 0; i < coursedataList.size(); i++){
            courseList.add(new AppCourseModel(coursedataList.get(i).getCourseName(), (i == 0 ? true : false)));
        }
//        courseList.add(new AppCourseModel("1", "UPSC", true));
//        courseList.add(new AppCourseModel("2", "SSC", false));
//        courseList.add(new AppCourseModel("3", "IBPS", false));
//        courseList.add(new AppCourseModel("4", "RBI", false));
//        courseList.add(new AppCourseModel("5", "SBI", false));
//        courseList.add(new AppCourseModel("6", "LIC", false));
//        courseList.add(new AppCourseModel("7", "RRB", false));
//        courseList.add(new AppCourseModel("8", "Railways", false));
//        courseList.add(new AppCourseModel("9", "Defence", false));
//        courseList.add(new AppCourseModel("10", "State", false));
        return courseList;
    }

    private List<Dashboardmodel.Testdata> getTestDataList(String courseName){
        List<Dashboardmodel.Testdata> testList = null;
        for(int i = 0; i < coursedataList.size(); i++){
            if(coursedataList.get(i).getCourseName().equals(courseName)){
                testList = coursedataList.get(i).getTests();
            }
        }

        for(int i = 0; i < testList.size(); i++){
            try {
                Date date = dateFormatInput.parse(testList.get(i).getDate());
                testList.get(i).setDate(dateFormatOutput.format(date));
                Date time = timeFormatInput.parse(testList.get(i).getTestTime());
                testList.get(i).setTestTime(timeFormatOutput.format(time));
                String clubbedDateTime = testList.get(i).getDate() + " " + testList.get(i).getTestTime();
                Date dateTime = dateTimeFormat.parse(clubbedDateTime);//"05/08/2020 04:00 PM"
                Date currentTime = Calendar.getInstance().getTime();
                long difference = dateTime.getTime() - currentTime.getTime();
                int days = (int) (difference / (1000*60*60*24));
                int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                hours = (hours < 0 ? -hours : hours);
                String timeLeft = (days > 0 ? days + " Days " :  (hours > 0 ? hours + " Hours " : "") + (min > 0 ? min + " Mins" : "" ));
                timeLeft = TextUtils.isEmpty(timeLeft) ? "" : "Ends in " + timeLeft;
                testList.get(i).setTimeLeft(timeLeft);
                //testList.get(i).setTimeLeft(outputDateTimeFormat.format(dateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return testList;
    }

    @Override
    public void onCourseClick(int position) {
        testDataList = getTestDataList(courseList.get(position).getCourse_name());
        courseItemAdapter.setItems(testDataList);
        for(int i = 0; i < courseList.size(); i++){
            courseList.get(i).setSelected(false);
        }
        courseList.get(position).setSelected(true);
        courseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCourseItemClick(int position) {
        Intent intent = new Intent(DashboardActivity.this, TestDetailActivity.class);
        Dashboardmodel.Testdata testdata= testDataList.get(position);
        intent.putExtra(TestDetailActivity.TEST_NAME, testdata.getName());
        intent.putExtra(TestDetailActivity.TIME_LEFT, testdata.getTimeLeft());
        intent.putExtra(TestDetailActivity.TOTAL_PRIZE, testdata.getTotalPrize());
        intent.putExtra(TestDetailActivity.ENTRY_FEE, testdata.getFee());
        intent.putExtra(TestDetailActivity.TOTAL_SPOTS, testdata.getNumberOfParticipants());
        intent.putExtra(TestDetailActivity.FIRST_PRIZE, testdata.getFirstPrize());
        intent.putExtra(TestDetailActivity.PRIZE_PERCENT, testdata.getWinPercentage());
//        intent.putExtra(TestDetailActivity.UPTO_VALUE, testdata.get);
        startActivity(intent);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("dashboard_jsonresponse.txt");
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

