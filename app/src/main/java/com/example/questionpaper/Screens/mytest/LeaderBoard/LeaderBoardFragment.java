package com.example.questionpaper.Screens.mytest.LeaderBoard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LeaderBoard.LeaderBoardResponse;
import com.example.questionpaper.Response.mytests.Requests.MyTests.DetailedAnalysisRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardFragment  extends Fragment {

    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;

    RecyclerView rViewCommon;
    RecyclerView.Adapter adapter;
    TextView tvErrorMessage;
    ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.my_tests_leader_board_fragment, container, false);
        pDialog= Utility.getProgressDialog(getContext());
        inItView(view);
        getLeaderBoardData();
        return view;
    }

    private void inItView(View v){
        imgNotes=v.findViewById(R.id.imgNotes);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);

        imgNotes.setVisibility(View.GONE);
        tvHeaderTitle.setText("Leader Board");

        rViewCommon = v.findViewById(R.id.rViewCommon);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rViewCommon.setLayoutManager(layoutManager);

        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
    private void getLeaderBoardData(){

        pDialog.show();

        String testId = "1";
        String userId = "";
        final DetailedAnalysisRequest userTestRequest = new DetailedAnalysisRequest(testId, userId);
        Call<LeaderBoardResponse> call = RetrofitClient.getInstance().getApi().leaderBoardAPI(userTestRequest);
        call.enqueue(new Callback<LeaderBoardResponse>() {
            @Override
            public void onResponse(Call<LeaderBoardResponse> call, Response<LeaderBoardResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        rViewCommon.setVisibility(View.GONE);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LeaderBoardResponse> call, Throwable t) {
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCommon.setVisibility(View.GONE);
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(LeaderBoardResponse response) {
        if(response !=null) {
            //Data to recyclerView
            if (response.getUserTestRanks() != null && response.getUserTestRanks().size() > 0) {
                tvErrorMessage.setVisibility(View.GONE);
                rViewCommon.setVisibility(View.VISIBLE);
                adapter = new LeaderBoardAdapter(response.getUserTestRanks());
                rViewCommon.setAdapter(adapter);
            }else{
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCommon.setVisibility(View.GONE);
            }
        }else{
            tvErrorMessage.setVisibility(View.VISIBLE);
            rViewCommon.setVisibility(View.GONE);
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
}
