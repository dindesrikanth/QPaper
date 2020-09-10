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
import com.example.questionpaper.Response.mytests.Requests.CompletedTestsRequest;
import com.example.questionpaper.Response.mytests.completed.CompletedTestsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompletedTestsFragment  extends Fragment {
    private static final String TAG = CompletedTestsFragment.class.getName();
    private RecyclerView rViewCommon;
    private TextView tvErrorMessage;
    private ProgressDialog pDialog;
    private CompletedTestsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.mytests_completed_fragment, container, false);
        inItView(v);
        pDialog= Utility.getProgressDialog(getActivity());

        return v;
    }
    private void inItView(View v) {
        tvErrorMessage=v.findViewById(R.id.tvErrorMessage);

        rViewCommon= v.findViewById(R.id.rViewCommon);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rViewCommon.setLayoutManager(layoutManager);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(!getUserVisibleHint()){
            return;
        }
        getUpComingTestData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isResumed()){
            onResume();
        }
    }
    private void getUpComingTestData(){
        pDialog.show();
        String numberOfMonths = "1";
        String userId = "1";
        final CompletedTestsRequest userTestRequest = new CompletedTestsRequest(numberOfMonths, userId);
        Call<CompletedTestsResponse> call = RetrofitClient.getInstance().getApi().completedTestData(userTestRequest);
        call.enqueue(new Callback<CompletedTestsResponse>() {
            @Override
            public void onResponse(Call<CompletedTestsResponse> call, Response<CompletedTestsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        // Toast.makeText(getContext(),response+": if",Toast.LENGTH_LONG).show();
                        //  Log.i(TAG, "==== Response ===" + response.body());
                        // Log.w("==== Response === ",new Gson().toJson(response));
                        showData(response.body());
                    }else{
                        // showMessageAndCloseScreen();
                        // Toast.makeText(getContext(),response+": else",Toast.LENGTH_LONG).show();
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        rViewCommon.setVisibility(View.GONE);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    // showMessageAndCloseScreen();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CompletedTestsResponse> call, Throwable t) {
                //showMessageAndCloseScreen();
               // Toast.makeText(getContext(),"failure",Toast.LENGTH_LONG).show();
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCommon.setVisibility(View.GONE);
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(CompletedTestsResponse response) {
        if(response !=null && response.getData()!=null && response.getData().size()>0){
            tvErrorMessage.setVisibility(View.GONE);
            rViewCommon.setVisibility(View.VISIBLE);
            adapter= new CompletedTestsAdapter(this,response.getData().get(0).getTests());
            rViewCommon.setAdapter(adapter);
        }else{
            tvErrorMessage.setVisibility(View.VISIBLE);
            rViewCommon.setVisibility(View.GONE);
        }
    }

}
