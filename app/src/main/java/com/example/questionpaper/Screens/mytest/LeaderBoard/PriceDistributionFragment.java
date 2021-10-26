package com.example.questionpaper.Screens.mytest.LeaderBoard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Adapter.PrizeAdapter;
import com.example.questionpaper.Common.Constants;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Model.PrizeModel;
import com.example.questionpaper.Model.PrizeResponseModel;
import com.example.questionpaper.Model.TestDetailRequestmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PriceDistributionFragment   extends Fragment {

    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;

    private ContainerActivity activity;
    private ProgressDialog pDialog;
    private RecyclerView rViewCommon;
    private TextView tvErrorMessage;
    private LinearLayout lnrHeader;

    PrizeAdapter prizeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.price_distribution_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        initViews(v);
        getPrizeData();
        return v;
    }

    private void initViews(View v) {
        imgNotes=v.findViewById(R.id.imgNotes);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        imgNotes.setVisibility(View.GONE);

        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Prize Distribution");

        lnrHeader= v.findViewById(R.id.lnrHeader);
        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);
        rViewCommon = v.findViewById(R.id.rViewCommon);
    }
    private void getPrizeData(){
        pDialog.show();
        Call<PrizeResponseModel> call = RetrofitClient.getInstance().getApi().getPrizeDetails(new TestDetailRequestmodel(Constants.testIdValue, Constants.prizeDistributionIdValue));
        call.enqueue(new Callback<PrizeResponseModel>() {
            @Override
            public void onResponse(Call<PrizeResponseModel> call, Response<PrizeResponseModel> response) {
                try {
                    if (response.isSuccessful()) {
                        lnrHeader.setVisibility(View.VISIBLE);
                        rViewCommon.setVisibility(View.VISIBLE);
                        tvErrorMessage.setVisibility(View.GONE);
                        List<PrizeModel> prizeModelList = response.body().getDistributions();
                        prizeAdapter = new PrizeAdapter(prizeModelList, getContext());
                        LinearLayoutManager  prizeLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rViewCommon.setLayoutManager(prizeLayoutManager);
                        rViewCommon.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                        rViewCommon.setAdapter(prizeAdapter);
                        pDialog.dismiss();
                    }else{
                        Utility.showCommonMessage(getActivity(),"Failed to load API...");
                        lnrHeader.setVisibility(View.GONE);
                        rViewCommon.setVisibility(View.GONE);
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        pDialog.dismiss();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    Utility.showCommonMessage(getActivity(),"Failed to load API...");
                    lnrHeader.setVisibility(View.GONE);
                    rViewCommon.setVisibility(View.GONE);
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PrizeResponseModel> call, Throwable t) {
                Utility.showCommonMessage(getActivity(),"Failed to load API...");
                pDialog.dismiss();
                return;
            }
        });
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
