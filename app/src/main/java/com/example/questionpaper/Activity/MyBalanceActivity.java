package com.example.questionpaper.Activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Adapter.ParticipantAdapter;
import com.example.questionpaper.Adapter.PrizeAdapter;
import com.example.questionpaper.Model.Leaderboardmodel;
import com.example.questionpaper.Model.ParticipantModel;
import com.example.questionpaper.Model.PrizeModel;
import com.example.questionpaper.Model.PrizeResponseModel;
import com.example.questionpaper.Model.TestDetailRequestmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBalanceActivity extends AppCompatActivity implements  View.OnClickListener{

    private LinearLayoutManager prizelayoutManager, contestantslayoutManager;
    private TextView test_title, test_time_left, prize_amount, test_fees, spots_left, total_spots, tv_first, tv_percentage, tv_upto, prize_title, participant_title, ll_prize_title_left, ll_prize_title_right, ll_participant_title_left;
    private View prize_selected_view, participant_selected_view;
    private ImageView iv_back, iv_wallet;
    private RelativeLayout test_detail_main_layout, rl_contestDetails, rl_leaderBoard;
    private ProgressBar test_detail_loader, slots_progress;
    private RecyclerView rv_prize_details, rv_contestants_details;

    private PrizeAdapter prizeAdapter;
    private ParticipantAdapter participantAdapter;

    List<PrizeModel> prizeModelList;
    List<ParticipantModel> participantModelList;
    int registeredUserCount;

    public static final String TEST_NAME = "TEST_NAME";
    public static final String TIME_LEFT = "TIME_LEFT";
    public static final String TOTAL_PRIZE = "TOTAL_PRIZE";
    public static final String ENTRY_FEE = "ENTRY_FEE";
    public static final String TOTAL_SPOTS = "TOTAL_SPOTS";
    public static final String FIRST_PRIZE = "FIRST_PRIZE";
    public static final String PRIZE_PERCENT = "PRIZE_PERCENT";

    String testName,timeLeft, entryFee;
    int totalPrize, totalSpots,firstPrize,prizePercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);
        testName = getIntent().getStringExtra(TEST_NAME);
        timeLeft = getIntent().getStringExtra(TIME_LEFT);
        entryFee = getIntent().getStringExtra(ENTRY_FEE);
        totalPrize = getIntent().getIntExtra(TOTAL_PRIZE, 0);
        totalSpots = getIntent().getIntExtra(TOTAL_SPOTS, 0);
        firstPrize = getIntent().getIntExtra(FIRST_PRIZE, 0);
        prizePercent = getIntent().getIntExtra(PRIZE_PERCENT, 0);
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
        test_title = findViewById(R.id.test_title);
        test_time_left = findViewById(R.id.test_time_left);
        prize_amount = findViewById(R.id.prize_amount);
        test_fees = findViewById(R.id.test_fees);
        spots_left = findViewById(R.id.spots_left);
        total_spots = findViewById(R.id.total_spots);
        tv_first = findViewById(R.id.tv_first);
        tv_percentage = findViewById(R.id.tv_percentage);
        tv_upto = findViewById(R.id.tv_upto);
        iv_back = findViewById(R.id.iv_back);
        iv_wallet = findViewById(R.id.iv_wallet);
        test_detail_main_layout = findViewById(R.id.test_detail_main_layout);
        rl_contestDetails = findViewById(R.id.rl_contestDetails);
        rl_leaderBoard = findViewById(R.id.rl_leaderBoard);
        test_detail_loader = findViewById(R.id.test_detail_loader);
        slots_progress = findViewById(R.id.slots_progress);
        rv_prize_details = findViewById(R.id.rv_prize_details);
        rv_contestants_details = findViewById(R.id.rv_contestants_details);
        prize_selected_view = findViewById(R.id.prize_selected_view);
        participant_selected_view = findViewById(R.id.participant_selected_view);
        prize_title = findViewById(R.id.prize_title);
        participant_title = findViewById(R.id.participant_title);
        ll_prize_title_left = findViewById(R.id.ll_prize_title_left);
        ll_prize_title_right = findViewById(R.id.ll_prize_title_right);
        ll_participant_title_left = findViewById(R.id.ll_participant_title_left);
        slots_progress.setProgressTintList(ColorStateList.valueOf(getColor(R.color.test_button_blue)));

        test_title.setText(testName);
        test_time_left.setText(timeLeft);
        prize_amount.setText(totalPrize + "");
        test_fees.setText(entryFee);
        total_spots.setText(totalSpots + "");
        tv_first.setText(firstPrize + "");
        tv_percentage.setText(prizePercent + "%");
        slots_progress.setMax(totalSpots);

        test_detail_main_layout.setVisibility(View.GONE);
        test_detail_loader.setVisibility(View.VISIBLE);
        rl_contestDetails.setOnClickListener(this);
        rl_leaderBoard.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_wallet.setOnClickListener(this);
        getTestDetailData();
    }

    private void setPrizeView(){
        try {
            prizelayoutManager = new LinearLayoutManager(MyBalanceActivity.this, LinearLayoutManager.VERTICAL, false);
            getPrizeData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setParticipantView(){
        try {
                contestantslayoutManager = new LinearLayoutManager(MyBalanceActivity.this, LinearLayoutManager.VERTICAL, false);
                getLeaderBoardData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private void getTestDetailData(){
//        Call<Object> call = RetrofitClient.getInstance().getApi().getAllQuesDetails();
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                try {
//                    if (response.isSuccessful()) {
//                        List<Questionesmodel> list = (List<Questionesmodel>)response.body();
//                        Gson gson = new Gson();
//
//                    }else{
//                        showMessageAndCloseScreen();
//                    }
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                    showMessageAndCloseScreen();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                showMessageAndCloseScreen();
//                return;
//            }
//        });

        setPrizeView();
        setParticipantView();
    }

    private void showMessageAndCloseScreen(){
        Toast.makeText(MyBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.iv_back:
//                    Toast.makeText(this, "Back Clicked", Toast.LENGTH_SHORT).show();
                    finish();
                    break;

                case R.id.iv_wallet:
                    Toast.makeText(this, "Wallet Clicked", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.rl_contestDetails:
                    openPrizeScreen();
                    break;

                case R.id.rl_leaderBoard:
                    openLeaderBoardScreen();
                    break;

                default:
                    break;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void openPrizeScreen(){
        rv_prize_details.setVisibility(View.VISIBLE);
        rv_contestants_details.setVisibility(View.GONE);
        ll_prize_title_left.setVisibility(View.VISIBLE);
        ll_prize_title_right.setVisibility(View.VISIBLE);
        ll_participant_title_left.setVisibility(View.GONE);
        prize_title.setTextColor(getColor(R.color.test_button_blue));
        prize_selected_view.setBackgroundColor(getColor(R.color.test_button_blue));
        participant_title.setTextColor(getColor(R.color.black));
        participant_selected_view.setBackgroundColor(getColor(R.color.white));
    }

    private void openLeaderBoardScreen(){
        rv_prize_details.setVisibility(View.GONE);
        rv_contestants_details.setVisibility(View.VISIBLE);
        ll_prize_title_left.setVisibility(View.GONE);
        ll_prize_title_right.setVisibility(View.GONE);
        ll_participant_title_left.setVisibility(View.VISIBLE);
        prize_title.setTextColor(getColor(R.color.black));
        prize_selected_view.setBackgroundColor(getColor(R.color.white));
        participant_title.setTextColor(getColor(R.color.test_button_blue));
        participant_selected_view.setBackgroundColor(getColor(R.color.test_button_blue));
        setParticipantView();
    }

    private void getLeaderBoardData(){
        Call<Leaderboardmodel> call = RetrofitClient.getInstance().getApi().getLeaderBoardDetails();
        call.enqueue(new Callback<Leaderboardmodel>() {
            @Override
            public void onResponse(Call<Leaderboardmodel> call, Response<Leaderboardmodel> response) {
                try {
                    if (response.isSuccessful()) {


//                        Gson gson = new Gson();
//                        String json = loadJSONFromAsset();
//                        Dashboardmodel dashboardmodel = gson.fromJson(json, Dashboardmodel.class);
//                        coursedataList = dashboardmodel.getData();


                        List<String> participantList= response.body().getUsernames();
                        registeredUserCount = response.body().getRegisteredUserCount();
                        participantAdapter = new ParticipantAdapter(participantList, getApplicationContext());
                        rv_contestants_details.setLayoutManager(contestantslayoutManager);
                        rv_contestants_details.addItemDecoration(new DividerItemDecoration(rv_contestants_details.getContext(), DividerItemDecoration.VERTICAL));
//            participantAdapter.setOnItemClickListener(TestDetailActivity.this);
                        rv_contestants_details.setAdapter(participantAdapter);
                    }else{
                        showMessageAndCloseScreen();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<Leaderboardmodel> call, Throwable t) {
                showMessageAndCloseScreen();
                return;
            }
        });
    }

    private void getPrizeData(){
        Call<PrizeResponseModel> call = RetrofitClient.getInstance().getApi().getPrizeDetails(new TestDetailRequestmodel(1, 3));
        call.enqueue(new Callback<PrizeResponseModel>() {
            @Override
            public void onResponse(Call<PrizeResponseModel> call, Response<PrizeResponseModel> response) {

                try {
                    if (response.isSuccessful()) {
                        test_detail_main_layout.setVisibility(View.VISIBLE);
                        test_detail_loader.setVisibility(View.GONE);

//                        Gson gson = new Gson();
//                        String json = loadJSONFromAsset();
//                        PrizeResponseModel prizeResponseModel = gson.fromJson(json, PrizeResponseModel.class);
//                        PrizeResponseModel prizeResponseModel = get
                        prizeModelList = response.body().getDistributions();
                        registeredUserCount = response.body().getRegisteredUserCount();
                        setSlotDetails();
//                        List<String> participantList= response.body().getUsernames();
                        prizeAdapter = new PrizeAdapter(prizeModelList, getApplicationContext());
                        rv_prize_details.setLayoutManager(prizelayoutManager);
                        rv_prize_details.addItemDecoration(new DividerItemDecoration(rv_prize_details.getContext(), DividerItemDecoration.VERTICAL));
//            participantAdapter.setOnItemClickListener(TestDetailActivity.this);
                        rv_prize_details.setAdapter(prizeAdapter);
                    }else{
                        showMessageAndCloseScreen();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<PrizeResponseModel> call, Throwable t) {
                showMessageAndCloseScreen();
                return;
            }
        });
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

    private List<PrizeModel> getPrizeList(){
        List<PrizeModel> prizeList = new ArrayList<>();
//        prizeList.add(new PrizeModel("1", "1", "1 Lakh"));
//        prizeList.add(new PrizeModel("2", "2 - 10", "10,000"));
//        prizeList.add(new PrizeModel("3", "11 - 20", "1000"));
//        prizeList.add(new PrizeModel("4", "21 - 40", "850"));
//        prizeList.add(new PrizeModel("5", "41 - 80", "500"));
//        prizeList.add(new PrizeModel("6", "81 - 200", "200"));
        return prizeList;
    }

    private List<ParticipantModel> getParticipantList(){
        List<ParticipantModel> participantList = new ArrayList<>();
        participantList.add(new ParticipantModel("1", "Malik"));
        participantList.add(new ParticipantModel("2", "Naveen"));
        participantList.add(new ParticipantModel("3", "Kishore"));
        participantList.add(new ParticipantModel("4", "hari"));
        participantList.add(new ParticipantModel("5", "Krish"));
        participantList.add(new ParticipantModel("6", "Harry"));
        participantList.add(new ParticipantModel("7", "John"));
        participantList.add(new ParticipantModel("8", "Raheem"));
        participantList.add(new ParticipantModel("9", "Syed"));
        participantList.add(new ParticipantModel("10", "Vijay"));
        return participantList;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("test_detail_response.txt");
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

    private void setSlotDetails(){
        slots_progress.setProgress(registeredUserCount);
        spots_left.setText((totalSpots - registeredUserCount) + " " + getString(R.string.spots_left));
    }
}

