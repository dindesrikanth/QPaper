package com.example.questionpaper.Screens.mytest.review;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.MyTests.review.ExamReviewRequest;
import com.example.questionpaper.Response.mytests.Review.ExamReviewResponse;
import com.google.android.gms.common.util.CollectionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamReviewScreenFragment  extends Fragment implements ExamReviewScreenAdapter.ObjectionInterface{
    ViewPager viewPager;
    ContainerActivity activity;
    LinearLayout lnrSliderDots;
    private int dotscount;
    private ImageView[] dots;
    private boolean isObjectionRequired;

    private ProgressDialog pDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.exam_review_screen_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog=Utility.getProgressDialog(getActivity());
        initViews(v);
        return v;
    }

    private void initViews(View v){
        viewPager = (ViewPager)v.findViewById(R.id.viewPager2);
        lnrSliderDots = (LinearLayout)v.findViewById(R.id.lnrSliderDots);
        getUserInfoData();
    }

    private void getUserInfoData(){
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
        ExamReviewRequest reviewRequest=new ExamReviewRequest("2",userId);

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
        if(response !=null && !CollectionUtils.isEmpty(response.getExamReviewsList())){
            isObjectionRequired= response.isObjectionRequired();
            ExamReviewScreenAdapter viewPagerAdapter = new ExamReviewScreenAdapter(getContext(),response.getExamReviewsList(),this);
            viewPager.setAdapter(viewPagerAdapter);
            dotscount = viewPagerAdapter.getCount();
            dots = new ImageView[dotscount];
            for(int i = 0; i < dotscount; i++){
                dots[i] = new ImageView(getContext());
                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                lnrSliderDots.addView(dots[i], params);
            }
            dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    // Empty method
                }

                @Override
                public void onPageSelected(int position) {
                    for(int i = 0; i< dotscount; i++){
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                    }
                    dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    // Empty method
                }
            });
        }else{
            Toast.makeText(getContext(),"else condition- empty list",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onObjectionItemClicked(int position) {
        if(isObjectionRequired) {
            ObjectionScreenDialogFragment fragment = new ObjectionScreenDialogFragment();
            fragment.show(getChildFragmentManager(), "objection");
        }
    }
}
