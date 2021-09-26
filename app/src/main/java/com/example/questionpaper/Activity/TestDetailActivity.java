package com.example.questionpaper.Activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestDetailActivity extends Fragment implements  View.OnClickListener{

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

    public static final String TEST_ID = "TEST_ID";
    public static final String PRIZE_DISTRIBUTION_ID = "PRIZE_DISTRIBUTION_ID";

    String testName,timeLeft, entryFee;
    int totalPrize, totalSpots,firstPrize,prizePercent,testId,prizeDistributionId;

    ContainerActivity activity;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_test_detail,container,false);
        this.activity=(ContainerActivity) getActivity();

        Bundle bundle = getArguments();
        if(null != bundle){
            testName = bundle.getString(TEST_NAME);
            timeLeft = bundle.getString(TIME_LEFT);
            entryFee = bundle.getString(ENTRY_FEE);
            totalPrize = bundle.getInt(TOTAL_PRIZE, 0);
            totalSpots = bundle.getInt(TOTAL_SPOTS, 0);
            firstPrize = bundle.getInt(FIRST_PRIZE, 0);
            prizePercent = bundle.getInt(PRIZE_PERCENT, 0);

            testId = bundle.getInt(TEST_ID, 0);
            prizeDistributionId = bundle.getInt(PRIZE_DISTRIBUTION_ID, 0);
        }
        initViews();
        return v;
    }


    private void initViews(){
        test_title = v.findViewById(R.id.test_title);
        test_time_left = v.findViewById(R.id.test_time_left);
        prize_amount = v.findViewById(R.id.prize_amount);
        test_fees = v.findViewById(R.id.test_fees);
        spots_left = v.findViewById(R.id.spots_left);
        total_spots = v.findViewById(R.id.total_spots);
        tv_first = v.findViewById(R.id.tv_first);
        tv_percentage = v.findViewById(R.id.tv_percentage);
        tv_upto = v.findViewById(R.id.tv_upto);
        iv_back = v.findViewById(R.id.iv_back);
        iv_wallet = v.findViewById(R.id.iv_wallet);
        test_detail_main_layout = v.findViewById(R.id.test_detail_main_layout);
        rl_contestDetails = v.findViewById(R.id.rl_contestDetails);
        rl_leaderBoard = v.findViewById(R.id.rl_leaderBoard);
        test_detail_loader = v.findViewById(R.id.test_detail_loader);
        slots_progress = v.findViewById(R.id.slots_progress);
        rv_prize_details = v.findViewById(R.id.rv_prize_details);
        rv_contestants_details = v.findViewById(R.id.rv_contestants_details);
        prize_selected_view = v.findViewById(R.id.prize_selected_view);
        participant_selected_view = v.findViewById(R.id.participant_selected_view);
        prize_title = v.findViewById(R.id.prize_title);
        participant_title = v.findViewById(R.id.participant_title);
        ll_prize_title_left = v.findViewById(R.id.ll_prize_title_left);
        ll_prize_title_right = v.findViewById(R.id.ll_prize_title_right);
        ll_participant_title_left = v.findViewById(R.id.ll_participant_title_left);
        slots_progress.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.test_button_blue)));

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
            prizelayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            getPrizeData();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setParticipantView(){
        try {
                contestantslayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                getLeaderBoardData();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.VISIBLE);
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
        Toast.makeText(getContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.iv_back:
                    getFragmentManager().popBackStack();
                    break;

                case R.id.iv_wallet:
                    Intent intent = new Intent(getContext(), AddBalanceActivity.class);
                    startActivity(intent);
                    break;

                case R.id.rl_contestDetails:
                    openPrizeScreen();
                    break;

                case R.id.rl_leaderBoard:
                    openLeaderBoardScreen();
                    break;
                case R.id.test_fees:
                    Toast.makeText(getContext(),"entry clicked...",Toast.LENGTH_LONG).show();
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
        prize_title.setTextColor(getResources().getColor(R.color.test_button_blue));
        prize_selected_view.setBackgroundColor(getResources().getColor(R.color.test_button_blue));
        participant_title.setTextColor(getResources().getColor(R.color.black));
        participant_selected_view.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void openLeaderBoardScreen(){
        rv_prize_details.setVisibility(View.GONE);
        rv_contestants_details.setVisibility(View.VISIBLE);
        ll_prize_title_left.setVisibility(View.GONE);
        ll_prize_title_right.setVisibility(View.GONE);
        ll_participant_title_left.setVisibility(View.VISIBLE);
        prize_title.setTextColor(getResources().getColor(R.color.black));
        prize_selected_view.setBackgroundColor(getResources().getColor(R.color.white));
        participant_title.setTextColor(getResources().getColor(R.color.test_button_blue));
        participant_selected_view.setBackgroundColor(getResources().getColor(R.color.test_button_blue));
        setParticipantView();
    }

    private void getLeaderBoardData(){
        Call<Leaderboardmodel> call = RetrofitClient.getInstance().getApi().getLeaderBoardDetails();
        call.enqueue(new Callback<Leaderboardmodel>() {
            @Override
            public void onResponse(Call<Leaderboardmodel> call, Response<Leaderboardmodel> response) {
                try {
                    if (response.isSuccessful()) {
                        List<String> participantList= response.body().getUsernames();
                        registeredUserCount = response.body().getRegisteredUserCount();
                        participantAdapter = new ParticipantAdapter(participantList, getContext());
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
        Call<PrizeResponseModel> call = RetrofitClient.getInstance().getApi().getPrizeDetails(new TestDetailRequestmodel(testId, prizeDistributionId));
        call.enqueue(new Callback<PrizeResponseModel>() {
            @Override
            public void onResponse(Call<PrizeResponseModel> call, Response<PrizeResponseModel> response) {

                try {
                    if (response.isSuccessful()) {
                        test_detail_main_layout.setVisibility(View.VISIBLE);
                        test_detail_loader.setVisibility(View.GONE);
                        prizeModelList = response.body().getDistributions();
                        registeredUserCount = response.body().getRegisteredUserCount();
                        setSlotDetails();
//                        List<String> participantList= response.body().getUsernames();
                        prizeAdapter = new PrizeAdapter(prizeModelList, getContext());
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

    private void setSlotDetails(){
        slots_progress.setProgress(registeredUserCount);
        spots_left.setText((totalSpots - registeredUserCount) + " " + getString(R.string.spots_left));
    }
}

