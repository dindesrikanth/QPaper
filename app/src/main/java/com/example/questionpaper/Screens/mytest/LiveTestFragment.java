package com.example.questionpaper.Screens.mytest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTestResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LiveTestFragment extends Fragment {

    RecyclerView rViewCommon;
    ArrayList<LiveTestResponse> liveTestResponseArrayList;

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
        getLiveTestsData();
        return view;
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
                        String responseJson = new Gson().toJson(response);
//                        liveTestResponseArrayList = new Gson().fromJson(responseJson,LiveTestResponse.class);


                    } else {
                        // showMessageAndCloseScreen();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // showMessageAndCloseScreen();
                }
            }

            @Override
            public void onFailure(Call<LiveTestResponse> call, Throwable t) {
                //showMessageAndCloseScreen();
                return;
            }
        });
    }

    private void intializeviews(View view) {
        /*rViewCommon = (RecyclerView)view.findViewById(R.id.rViewCommon);
        LiveTestAdapter liveTestAdapter =  new LiveTestAdapter(getActivity());
        rViewCommon.setLayoutManager(new LinearLayoutManager(getContext()));
        rViewCommon.setAdapter(liveTestAdapter);*/
    }

}


