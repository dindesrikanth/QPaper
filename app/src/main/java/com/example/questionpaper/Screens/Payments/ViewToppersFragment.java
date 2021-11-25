package com.example.questionpaper.Screens.Payments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.Payments.ViewToppersListResponse;
import com.google.android.gms.common.util.CollectionUtils;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewToppersFragment  extends Fragment {
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;
    ContainerActivity activity;
    private ProgressDialog pDialog;
    private RecyclerView rViewCommon;
    private TextView tvErrorMessage;
    private ViewToppersAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.transaction_history_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        setHeaderFooter(v);
        initViews(v);
        getToppersListData();
        return v;
    }
    private void setHeaderFooter(View v) {
        imgNotes = v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle = v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Toppers");
        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
    private void initViews(View v){
        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);
        rViewCommon = v.findViewById(R.id.rViewCommon);
    }

    private void getToppersListData() {
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
        Call<ViewToppersListResponse> call = RetrofitClient.getInstance().getApi().getToppersList(userId);
        call.enqueue(new Callback<ViewToppersListResponse>() {
            @Override
            public void onResponse(Call<ViewToppersListResponse> call, Response<ViewToppersListResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        //Toast.makeText(getActivity(),"header"+response.headers(),Toast.LENGTH_LONG).show();
                        showData(response.body());
                    }else{
                        Utility.showCommonMessage(getActivity(),"Failed to load API...");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ViewToppersListResponse> call, Throwable t) {
                // Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(ViewToppersListResponse response) {
        if (response != null && !CollectionUtils.isEmpty(response.getTopTestRanks())) {
            rViewCommon.setVisibility(View.VISIBLE);
            tvErrorMessage.setVisibility(View.GONE);

            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
                rViewCommon.setLayoutManager(layoutManager);
                adapter = new ViewToppersAdapter(response.getTopTestRanks());
                rViewCommon.setAdapter(adapter);
        }else{
            rViewCommon.setVisibility(View.GONE);
            tvErrorMessage.setVisibility(View.VISIBLE);
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
