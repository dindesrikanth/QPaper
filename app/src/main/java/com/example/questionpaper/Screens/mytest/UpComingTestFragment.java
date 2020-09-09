package com.example.questionpaper.Screens.mytest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.UpcomingTestsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpComingTestFragment extends Fragment {

    RecyclerView rViewCommon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.mytests_upcoming_fragment, container, false);
        inItView(v);
        getUpComingTestData();

        return v;
    }

    private void getUpComingTestData(){
        Call<UpcomingTestsResponse> call = RetrofitClient.getInstance().getApi().upcomingTestsData("E","1");
        call.enqueue(new Callback<UpcomingTestsResponse>() {
            @Override
            public void onResponse(Call<UpcomingTestsResponse> call, Response<UpcomingTestsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        
                    }else{
                       // showMessageAndCloseScreen();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                   // showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<UpcomingTestsResponse> call, Throwable t) {
                //showMessageAndCloseScreen();
                return;
            }
        });
    }

    private void inItView(View v) {
        rViewCommon= v.findViewById(R.id.rViewCommon);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rViewCommon.setLayoutManager(layoutManager);
    }
}
