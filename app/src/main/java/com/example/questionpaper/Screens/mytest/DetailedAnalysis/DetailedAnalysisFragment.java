package com.example.questionpaper.Screens.mytest.DetailedAnalysis;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.DetailedAnalysis.DetailedAnalysisResponse;
import com.example.questionpaper.Response.mytests.Requests.MyTests.DetailedAnalysisRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailedAnalysisFragment extends Fragment implements View.OnClickListener {
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;

    private TextView tvTestNameValue,tvRankValue,tvScoreValue,tvAmountValue;
    private TextView tvPurchase,tvView,tvLeaderBoardView;
    private RecyclerView rViewCommon;
    private RecyclerView.Adapter adapter;
    private TextView tvErrorMessage;
    private ProgressDialog pDialog;
    private ContainerActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_detailed_analysis, container, false);
        pDialog= Utility.getProgressDialog(getContext());
        this.activity=(ContainerActivity)getActivity();
        inItView(view);
        setEnableListeners();
        getDetailedAnalysisData();
        return view;
    }
    private void inItView(View v){
        //Header part
        imgNotes=v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Detailed Analysis");

        tvTestNameValue=v.findViewById(R.id.tvTestNameValue);
        tvRankValue=v.findViewById(R.id.tvRankValue);
        tvScoreValue=v.findViewById(R.id.tvScoreValue);
        tvAmountValue=v.findViewById(R.id.tvAmountValue);

        tvPurchase=v.findViewById(R.id.tvPurchase);
        tvView = v.findViewById(R.id.tvView);
        tvLeaderBoardView=v.findViewById(R.id.tvLeaderBoardView);

        rViewCommon = v.findViewById(R.id.rViewCommon);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rViewCommon.setLayoutManager(layoutManager);

        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);
    }
    private void setEnableListeners(){
        imgBackArrow.setOnClickListener(this);
        tvPurchase.setOnClickListener(this);
        tvView.setOnClickListener(this);
        tvLeaderBoardView.setOnClickListener(this);
    }

    private void getDetailedAnalysisData(){

        pDialog.show();

        String testId = "1";
        String userId = "1";
        final DetailedAnalysisRequest userTestRequest = new DetailedAnalysisRequest(testId, userId);
        Call<DetailedAnalysisResponse> call = RetrofitClient.getInstance().getApi().detailedAnalysisAPI(userTestRequest);
        call.enqueue(new Callback<DetailedAnalysisResponse>() {
            @Override
            public void onResponse(Call<DetailedAnalysisResponse> call, Response<DetailedAnalysisResponse> response) {
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
            public void onFailure(Call<DetailedAnalysisResponse> call, Throwable t) {
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCommon.setVisibility(View.GONE);
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(DetailedAnalysisResponse response) {
        if(response !=null) {
            tvTestNameValue.setText(response.getTestName());
            tvRankValue.setText(response.getRank());
            tvScoreValue.setText(response.getScore());
            tvAmountValue.setText(response.getAmountWon());

            //Data to recyclerView
            if (response.getDetailedAnalysis() != null && response.getDetailedAnalysis().size() > 0) {
                tvErrorMessage.setVisibility(View.GONE);
                rViewCommon.setVisibility(View.VISIBLE);
                adapter = new DetailedAnalysisAdapter(response.getDetailedAnalysis());
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
    public void onClick(View view) {

        int id= view.getId();
        switch (id){
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.tvPurchase:
                Toast.makeText(getContext(),"Purchase clicked ...",Toast.LENGTH_LONG).show();
                break;
            case R.id.tvView:
                Toast.makeText(getContext(),"View clicked ...",Toast.LENGTH_LONG).show();
                break;
            case R.id.tvLeaderBoardView:
                activity.displayFragment(3);
                break;
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