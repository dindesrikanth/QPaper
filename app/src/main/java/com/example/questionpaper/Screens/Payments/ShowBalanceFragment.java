package com.example.questionpaper.Screens.Payments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionpaper.Activity.AddBalanceActivity;
import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.Payments.ShowBalanceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowBalanceFragment extends Fragment implements View.OnClickListener{
    ContainerActivity activity;
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;
    TextView tvTotalBalance, tvAddCash,tvAddedBalance,tvWinningsBalance, tvRewardBonus;
    ImageView imgAmountAdded,imgWinnings,imgReward;
    LinearLayout lnrKycDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.show_balance_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        initViews(v);
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.GONE);
        getBalanceData();
    }

    @Override
    public void onStop() {
        super.onStop();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.VISIBLE);
    }
    private void initViews(View v){
        imgNotes=v.findViewById(R.id.imgNotes);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        imgBackArrow.setOnClickListener(this);

        imgNotes.setVisibility(View.GONE);

        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("My Balance");

        lnrKycDetails = v.findViewById(R.id.lnrKycDetails);
        lnrKycDetails.setOnClickListener(this);
        tvTotalBalance= v.findViewById(R.id.tvTotalBalance);
        tvAddCash= v.findViewById(R.id.tvAddCash);
        tvAddCash.setOnClickListener(this);

        tvAddedBalance= v.findViewById(R.id.tvAddedBalance);
        imgAmountAdded= v.findViewById(R.id.imgAmountAdded);
        imgAmountAdded.setOnClickListener(this);

        tvWinningsBalance= v.findViewById(R.id.tvWinningsBalance);
        imgWinnings= v.findViewById(R.id.imgWinnings);
        imgWinnings.setOnClickListener(this);

        tvRewardBonus= v.findViewById(R.id.tvRewardBonus);
        imgReward= v.findViewById(R.id.imgReward);
        imgReward.setOnClickListener(this);
    }

    private void getBalanceData(){
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());

        Call<ShowBalanceResponse> call = RetrofitClient.getInstance().getApi().getWalletData(userId);
        call.enqueue(new Callback<ShowBalanceResponse>() {
            @Override
            public void onResponse(Call<ShowBalanceResponse> call, Response<ShowBalanceResponse> response) {
                try {
                    if (response.isSuccessful()) {
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
            public void onFailure(Call<ShowBalanceResponse> call, Throwable t) {
                Utility.showCommonMessage(getContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(ShowBalanceResponse response) {
        if(response !=null) {
            tvAddedBalance.setText("₹"+response.getWalletBalance());
            tvWinningsBalance.setText("₹"+response.getWinsBalance());
            tvRewardBonus.setText("₹"+response.getCashBonus());

            int totalBalance = 0;
            if(!TextUtils.isEmpty(response.getWalletBalance())){
                totalBalance = totalBalance + Integer.parseInt(response.getWalletBalance());
            }
            if(!TextUtils.isEmpty(response.getWinsBalance())){
                totalBalance = totalBalance + Integer.parseInt(response.getWinsBalance());
            }
            if(!TextUtils.isEmpty(response.getCashBonus())){
                totalBalance = totalBalance + Integer.parseInt(response.getCashBonus());
            }

            tvTotalBalance.setText("₹"+totalBalance);
        }
        else{
            Toast.makeText(getContext(), R.string.no_data_found,Toast.LENGTH_LONG).show();
            tvAddedBalance.setText("₹0");
            tvWinningsBalance.setText("₹0");
            tvRewardBonus.setText("₹0");
            tvTotalBalance.setText("₹0");
        }
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStack();
                activity.loadLandingFragment();
                break;
            case R.id.tvAddCash:
                Intent intent = new Intent(getContext(), AddBalanceActivity.class);
                startActivity(intent);
                break;
            case R.id.lnrKycDetails:
                activity.displayFragment(9);
                break;
            case R.id.imgAmountAdded:
                break;
            case R.id.imgWinnings:
                break;
            case R.id.imgReward:
                break;
            default:
                break;
        }
    }
}
