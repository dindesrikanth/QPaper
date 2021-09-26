package com.example.questionpaper.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Adapter.CourseAdapter;
import com.example.questionpaper.Adapter.CourseItemAdapter;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Model.AppCourseModel;
import com.example.questionpaper.Model.DashboardModelNew;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.google.android.gms.common.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardScreenFragment extends Fragment implements  View.OnClickListener, CourseAdapter.OnItemClickListener, CourseItemAdapter.OnItemClickListener{
    private LinearLayoutManager layoutManager;
    private TextView btn_notifications, btn_home, btn_more, btn_test;
   // private ImageView iv_user, iv_refresh;
    private RelativeLayout dashboard_main_layout, home_layout, test_layout, notification_layout, more_layout;
    private ProgressBar dashboard_loader;
    private RecyclerView rv_sub_header, rv_items;
    private static final int HOME_LAYOUT_ID = 1;
    private static final int TEST_LAYOUT_ID = 2;
    private static final int NOTIFICATIONS_LAYOUT_ID = 3;
    private static final int MORE_LAYOUT_ID = 4;
    int currentSelectedFooerId = -1;
    private List<DashboardModelNew.CourseSpecificTests> coursedataList;
    private List<DashboardModelNew.DashBoardTests> testDataList;

    private SimpleDateFormat dateFormatInput, dateFormatOutput, timeFormatInput, timeFormatOutput, dateTimeFormat, outputDateTimeFormat;

    private CourseAdapter courseAdapter;
    private CourseItemAdapter courseItemAdapter;
    List<AppCourseModel> courseList;
    ContainerActivity activity;

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.activity_dashboard,container,false);
        this.activity=(ContainerActivity) getActivity();
        initViews();
        return v;
    }

    private void initViews(){
        dateFormatInput = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        timeFormatInput = new SimpleDateFormat("HH:mm:ss");
        timeFormatOutput = new SimpleDateFormat("hh:mm a");
        dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        outputDateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        outputDateTimeFormat.setTimeZone(TimeZone.getDefault());
        btn_notifications = v.findViewById(R.id.btn_notifications);
        btn_home = v.findViewById(R.id.btn_home);
        btn_more = v.findViewById(R.id.btn_more);
        btn_test = v.findViewById(R.id.btn_test);
        home_layout = v.findViewById(R.id.home_layout);
        test_layout = v.findViewById(R.id.test_layout);
        notification_layout = v.findViewById(R.id.notification_layout);
        more_layout = v.findViewById(R.id.more_layout);
        rv_sub_header = v.findViewById(R.id.rv_sub_header);
        rv_items = v.findViewById(R.id.rv_items);
        dashboard_main_layout = v.findViewById(R.id.dashboard_main_layout);
        dashboard_loader = v.findViewById(R.id.dashboard_loader);
        dashboard_main_layout.setVisibility(View.GONE);
        dashboard_loader.setVisibility(View.VISIBLE);
       // iv_user.setOnClickListener(this);
       // iv_refresh.setOnClickListener(this);
        btn_notifications.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_more.setOnClickListener(this);
        btn_test.setOnClickListener(this);
        setFooter(HOME_LAYOUT_ID);
        getDashboardData();
    }

    private void setSubHeader(){
        try {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_sub_header = v.findViewById(R.id.rv_sub_header);
            courseList = getCourseList();
            if(!CollectionUtils.isEmpty(courseList)){
                courseAdapter = new CourseAdapter(courseList, getContext());
                rv_sub_header.setLayoutManager(layoutManager);
                courseAdapter.setOnItemClickListener(DashboardScreenFragment.this);
                rv_sub_header.setAdapter(courseAdapter);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setItems(){
        try {
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            rv_items = v.findViewById(R.id.rv_items);
            if(!CollectionUtils.isEmpty(coursedataList)) {
                testDataList = getTestDataList(coursedataList.get(0).getCourseName());
            }

            courseItemAdapter = new CourseItemAdapter(testDataList, getContext());
            rv_items.setLayoutManager(layoutManager);
            courseItemAdapter.setOnItemClickListener(DashboardScreenFragment.this);
            rv_items.setAdapter(courseItemAdapter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getDashboardData(){
        String userId = Utility.getUserIdFromSharedPref(getContext());
        Call<DashboardModelNew> call = RetrofitClient.getInstance().getApi().getDashboardDetailsNew(userId);
        call.enqueue(new Callback<DashboardModelNew>() {
            @Override
            public void onResponse(Call<DashboardModelNew> call, Response<DashboardModelNew> response) {
                try {
                    if (response.isSuccessful()) {
                        coursedataList = response.body().getCourseSpecificTests();
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
            public void onFailure(Call<DashboardModelNew> call, Throwable t) {
                showMessageAndCloseScreen();
                return;
            }
        });
    }

    private void showMessageAndCloseScreen(){
        Toast.makeText(getContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_notifications:
                    setFooter(NOTIFICATIONS_LAYOUT_ID);
                    break;

                case R.id.btn_test:
                    setFooter(TEST_LAYOUT_ID);
                    activity.displayFragment(0);
                    break;

                case R.id.btn_home:
                    setFooter(HOME_LAYOUT_ID);
                    break;

                case R.id.btn_more:
                    setFooter(MORE_LAYOUT_ID);
                    break;
                default:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void setFooter(int id){
        switch(currentSelectedFooerId){
            case HOME_LAYOUT_ID:
                btn_home.setTextColor(getResources().getColor(R.color.dashboard_grey));
                btn_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.icon_home_unselected) ,null,null);
                home_layout.setVisibility(View.GONE);
                break;

            case TEST_LAYOUT_ID:
                btn_test.setTextColor(getResources().getColor(R.color.dashboard_grey));
                btn_test.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.icon_degree_unselected) ,null,null);
                test_layout.setVisibility(View.GONE);
                break;

            case NOTIFICATIONS_LAYOUT_ID:
                btn_notifications.setTextColor(getResources().getColor(R.color.dashboard_grey));
                btn_notifications.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.icon_notification_bell_unselected) ,null,null);
                notification_layout.setVisibility(View.GONE);
                break;

            case MORE_LAYOUT_ID:
                btn_more.setTextColor(getResources().getColor(R.color.dashboard_grey));
                btn_more.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.icon_more_unselected) ,null,null);
                more_layout.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        switch(id){
            case HOME_LAYOUT_ID:
                btn_home.setTextColor(getResources().getColor(R.color.test_button_blue));
                Drawable drawableHome = getResources().getDrawable(R.drawable.icon_home_selected);
                drawableHome.setTint(getResources().getColor(R.color.test_button_blue));
                btn_home.setCompoundDrawablesWithIntrinsicBounds(null, drawableHome ,null,null);
                home_layout.setVisibility(View.VISIBLE);
                break;

            case TEST_LAYOUT_ID:
                btn_test.setTextColor(getResources().getColor(R.color.test_button_blue));
                Drawable drawableTest = getResources().getDrawable(R.drawable.icon_degree_selected);
                drawableTest.setTint(getResources().getColor(R.color.test_button_blue));
                btn_test.setCompoundDrawablesWithIntrinsicBounds(null, drawableTest ,null,null);
                test_layout.setVisibility(View.VISIBLE);
                break;

            case NOTIFICATIONS_LAYOUT_ID:
                btn_notifications.setTextColor(getResources().getColor(R.color.test_button_blue));
                Drawable drawableNotifications = getResources().getDrawable(R.drawable.icon_notification_bell_selected);
                drawableNotifications.setTint(getResources().getColor(R.color.test_button_blue));
                btn_notifications.setCompoundDrawablesWithIntrinsicBounds(null, drawableNotifications ,null,null);
                notification_layout.setVisibility(View.VISIBLE);
                break;

            case MORE_LAYOUT_ID:
                btn_more.setTextColor(getResources().getColor(R.color.test_button_blue));
                Drawable drawableMore = getResources().getDrawable(R.drawable.icon_more_selected);
                drawableMore.setTint(getResources().getColor(R.color.test_button_blue));
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

    private List<DashboardModelNew.DashBoardTests> getTestDataList(String courseName){
        List<DashboardModelNew.DashBoardTests> testList = null;
        for(int i = 0; i < coursedataList.size(); i++){
            if(coursedataList.get(i).getCourseName().equals(courseName)){
                testList = coursedataList.get(i).getDashBoardTests();
            }
        }

        for(int i = 0; i < testList.size(); i++){
            try {
                Date date = dateFormatInput.parse(testList.get(i).getTestDate());
                testList.get(i).setTestDate(dateFormatOutput.format(date));
                Date time = timeFormatInput.parse(testList.get(i).getTestTime());
                testList.get(i).setTestTime(timeFormatOutput.format(time));
                String clubbedDateTime = testList.get(i).getTestDate() + " " + testList.get(i).getTestTime();
                Date dateTime = dateTimeFormat.parse(clubbedDateTime);//"05/08/2020 04:00 PM"
                Date currentTime = Calendar.getInstance().getTime();
                long difference = dateTime.getTime() - currentTime.getTime();
                int days = (int) (difference / (1000*60*60*24));
                int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
                int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
                hours = (hours < 0 ? -hours : hours);
                String timeLeft = (days > 0 ? days + " Days " :  (hours > 0 ? hours + " Hours " : "") + (min > 0 ? min + " Mins" : "" ));
                timeLeft = TextUtils.isEmpty(timeLeft) ? "" : "Ends in " + timeLeft;
               // testList.get(i).setTimeLeft(timeLeft);
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
        Bundle b = new Bundle();
        DashboardModelNew.DashBoardTests testdata= testDataList.get(position);

        b.putString(TestDetailActivity.TEST_NAME, testdata.getTestName());
        //b.putString(TestDetailActivity.TIME_LEFT, testdata.getTimeLeft());
        b.putInt(TestDetailActivity.TOTAL_PRIZE, testdata.getTotalPrize());
        b.putString(TestDetailActivity.ENTRY_FEE, testdata.getTestFee());
        b.putInt(TestDetailActivity.TOTAL_SPOTS, testdata.getTotalParticipants());
        b.putInt(TestDetailActivity.FIRST_PRIZE, testdata.getFirstPrize());
        b.putInt(TestDetailActivity.PRIZE_PERCENT, testdata.getWinPercentage());

        b.putInt(TestDetailActivity.TEST_ID,Integer.parseInt(testdata.getTestId()));
        b.putInt(TestDetailActivity.PRIZE_DISTRIBUTION_ID,testdata.getPrizeDistributionId());

        TestDetailActivity detailActivity=new TestDetailActivity();
        detailActivity.setArguments(b);

        FragmentManager fManager= getFragmentManager();
        FragmentTransaction tr=fManager.beginTransaction();
        tr.replace(R.id.containerLayout,detailActivity);
        tr.addToBackStack(null);
        tr.commit();
        //startActivity(intent);
    }
}

