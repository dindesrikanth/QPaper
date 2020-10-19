package com.example.questionpaper.Screens.Courses;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.Courses.CoursesListSubmitRequest;
import com.example.questionpaper.Response.CommonResponse;
import com.example.questionpaper.Response.Courses.CoursesDetails;
import com.example.questionpaper.Response.Courses.CoursesListScreenResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesListScreenFragment extends Fragment implements CoursesListScreenAdapter.CoursesListInterface,View.OnClickListener{
    RecyclerView rViewCourseList;
    RecyclerView.Adapter adapter;
    TextView tvErrorMessage,tvSubmit;
    ProgressDialog pDialog;
    private boolean isCourseSelected= false;
    private List<CoursesDetails> coursesDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.courses_list_fragment, container, false);
        pDialog= Utility.getProgressDialog(getContext());
        inItView(view);
        getCoursesListData();
        return view;
    }

    private void inItView(View v){
        rViewCourseList = v.findViewById(R.id.rViewCommon);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        rViewCourseList.setLayoutManager(layoutManager);
        tvErrorMessage = v.findViewById(R.id.tvErrorMessage);
        tvSubmit = v.findViewById(R.id.tvSubmit);
        tvSubmit.setOnClickListener(this);
    }

    private void getCoursesListData(){
        pDialog.show();
        Call<CoursesListScreenResponse> call = RetrofitClient.getInstance().getApi().coursesListAPI();
        call.enqueue(new Callback<CoursesListScreenResponse>() {
            @Override
            public void onResponse(Call<CoursesListScreenResponse> call, Response<CoursesListScreenResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        rViewCourseList.setVisibility(View.GONE);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CoursesListScreenResponse> call, Throwable t) {
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCourseList.setVisibility(View.GONE);
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(CoursesListScreenResponse response) {
        if(response !=null) {
            //Data to recyclerView
            if (response.getCoursesDetails() != null && response.getCoursesDetails().size() > 0) {
                tvErrorMessage.setVisibility(View.GONE);
                rViewCourseList.setVisibility(View.VISIBLE);
                adapter = new CoursesListScreenAdapter(this,response.getCoursesDetails());
                rViewCourseList.setAdapter(adapter);
            }else{
                tvErrorMessage.setVisibility(View.VISIBLE);
                rViewCourseList.setVisibility(View.GONE);
            }
        }else{
            tvErrorMessage.setVisibility(View.VISIBLE);
            rViewCourseList.setVisibility(View.GONE);
        }
    }


    @Override
    public void onCourseSelected(int position, List<CoursesDetails> coursesDetailsList) {
        coursesDetailsList.get(position).setSelected(true);
        adapter.notifyDataSetChanged();

        this.isCourseSelected = true;
        tvSubmit.setBackgroundResource(R.drawable.box_border_blue_bg);
        tvSubmit.setTextColor(Color.parseColor("#FFFFFF"));
        coursesDetails= new ArrayList<>();
        coursesDetails.clear();
        coursesDetails.addAll(coursesDetailsList);
    }

    private void submitSelectedCourseList(){
        String id="";
        if(coursesDetails!=null && coursesDetails.size()>0){
            for (CoursesDetails details:coursesDetails){
                if(details.isSelected()){
                    id= id+details.getValue()+",";
                }
            }
        }
        if(!TextUtils.isEmpty(id)){
            id= id.substring(0, id.length() - 1);
            submitSelectedCoursesApi("2",id);
        }
    }
    private void submitSelectedCoursesApi(String userId, String preferredCourses){
        pDialog.show();
        final CoursesListSubmitRequest userTestRequest = new CoursesListSubmitRequest(preferredCourses, userId);
        Call<CommonResponse> call = RetrofitClient.getInstance().getApi().coursesSubmitAPI(userTestRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        Toast.makeText(getActivity(),"Invalid response...",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(CommonResponse response) {
        if(response !=null && response.getStatus().equalsIgnoreCase("success")) {
            Toast.makeText(getActivity(),"Course(s) list submitted successfully ...",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"failed to submit course details...",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        if(id == R.id.tvSubmit && isCourseSelected){
            submitSelectedCourseList();
        }else{
            Toast.makeText(getActivity(),"Please select courses you are preparing ...",Toast.LENGTH_LONG).show();
        }
    }
}
