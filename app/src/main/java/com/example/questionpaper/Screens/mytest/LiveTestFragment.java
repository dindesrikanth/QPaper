package com.example.questionpaper.Screens.mytest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTest.LiveTestResponse;
import com.example.questionpaper.Response.mytests.LiveTest.TestData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveTestFragment extends Fragment {

    RecyclerView rViewCommon;
    LiveTestResponse liveTestResponse;
    List<TestData> dataList;
    TextView tvErrorMessage;
    ProgressDialog pDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mytests_upcoming_fragment, container, false);
        intializeviews(view);
        pDialog= Utility.getProgressDialog(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!getUserVisibleHint()){
            return;
        }
        getLiveTestsData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isResumed()){
            onResume();
        }
    }

    private void getLiveTestsData() {
        String typeOfTests = "L";
        String userId = "1";
        final UserTestRequest userTestRequest = new UserTestRequest(typeOfTests, userId);
        Call<LiveTestResponse> call = RetrofitClient.getInstance().getApi().liveTestData(userTestRequest);
        call.enqueue(new Callback<LiveTestResponse>() {
            @Override
            public void onResponse(Call<LiveTestResponse> call, Response<LiveTestResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        String responseJson = new Gson().toJson(response.body());
                        showData(responseJson);
                    } else {
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        rViewCommon.setVisibility(View.GONE);
                        // showMessageAndCloseScreen();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // showMessageAndCloseScreen();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LiveTestResponse> call, Throwable t) {
                //showMessageAndCloseScreen();
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCommon.setVisibility(View.GONE);
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(String responseJson) {
        liveTestResponse = new Gson().fromJson(responseJson,LiveTestResponse.class);
        dataList = new ArrayList<>();
        if(liveTestResponse!=null){
            for(int i= 0; i<liveTestResponse.getData().size();i++){
                dataList.add(liveTestResponse.getData().get(i));
            }
        }
        if(dataList!=null && dataList.size()>0){
            LiveTestAdapter liveTestAdapter =  new LiveTestAdapter(getContext(),dataList);
            rViewCommon.setAdapter(liveTestAdapter);
        }else{
            tvErrorMessage.setVisibility(View.VISIBLE);
            rViewCommon.setVisibility(View.GONE);
        }
    }

    private void intializeviews(View view) {
        tvErrorMessage=view.findViewById(R.id.tvErrorMessage);
        rViewCommon = (RecyclerView)view.findViewById(R.id.rViewCommon);
        rViewCommon.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}


