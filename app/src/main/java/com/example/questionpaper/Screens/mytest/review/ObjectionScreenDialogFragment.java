package com.example.questionpaper.Screens.mytest.review;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.questionpaper.Common.CustomAdapter;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.MyTests.review.ExamReviewRequest;
import com.example.questionpaper.Response.mytests.Review.ExamReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObjectionScreenDialogFragment extends DialogFragment {

    CustomEditText edtUserName;
    EditText edtDescription;
    Spinner spnIssueWith,spnObjection;
    String selectedObjection="",selectedIssue="";
    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.objection_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pDialog=Utility.getProgressDialog(getActivity());
        edtUserName = view.findViewById(R.id.edtUserName);
        spnIssueWith= view.findViewById(R.id.spnIssueWith);
        spnObjection= view.findViewById(R.id.spnObjection);
        edtDescription=view.findViewById(R.id.edtDescription);

        edtUserName.setValueToLayout("Enter Question No","");

        setIssues();
        setObjections();
        Button btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfoData();
            }
        });
    }

    private void setIssues() {
        CustomAdapter customAdapter=new CustomAdapter(getContext(), Utility.listOfIsses);
        spnIssueWith.setAdapter(customAdapter);

        spnIssueWith.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedIssue = Utility.listOfIsses[position]+"";
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });
    }

    private void setObjections(){
        CustomAdapter customAdapter=new CustomAdapter(getContext(), Utility.listOfObjections);
        spnObjection.setAdapter(customAdapter);
        spnObjection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedObjection = Utility.listOfObjections[position]+"";
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });
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
                        Toast.makeText(getActivity(),"header"+response.headers(),Toast.LENGTH_LONG).show();

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



}
