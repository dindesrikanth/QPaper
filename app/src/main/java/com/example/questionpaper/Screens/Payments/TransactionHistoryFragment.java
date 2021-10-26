package com.example.questionpaper.Screens.Payments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Constants;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.MyTests.review.ExamReviewRequest;
import com.example.questionpaper.Response.mytests.Review.ExamReviewResponse;
import com.google.android.gms.common.util.CollectionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryFragment extends Fragment {
    ContainerActivity activity;
    private ProgressDialog pDialog;
    private RecyclerView rViewCommon;
    private TextView tvErrorMessage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.transaction_history_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        initViews(v);
        getTransactionHistoryData();
        return v;
    }

    private void getTransactionHistoryData() {
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
        String testId = Constants.testIdValue+"";
        ExamReviewRequest reviewRequest=new ExamReviewRequest(testId,userId);

        Call<ExamReviewResponse> call = RetrofitClient.getInstance().getApi().getUserExamReview(reviewRequest);
        call.enqueue(new Callback<ExamReviewResponse>() {
            @Override
            public void onResponse(Call<ExamReviewResponse> call, Response<ExamReviewResponse> response) {
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
            public void onFailure(Call<ExamReviewResponse> call, Throwable t) {
                // Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(ExamReviewResponse response) {
        if (response != null && !CollectionUtils.isEmpty(response.getExamReviewsList())) {

        }
    }

            private void initViews(View v){
        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);
        rViewCommon = v.findViewById(R.id.rViewCommon);
    }
}
