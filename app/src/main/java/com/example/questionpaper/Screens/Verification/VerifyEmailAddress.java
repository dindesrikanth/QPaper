package com.example.questionpaper.Screens.Verification;

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

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;

public class VerifyEmailAddress  extends Fragment implements View.OnClickListener {
    private ContainerActivity activity;
    private ProgressDialog pDialog;

    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvRightButton;

    private CustomEditText edtEmailId,edtOtp;
    private TextView tvResendOtp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.verify_email_address,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        setHeaderFooter(v);
        initViews(v);
        setListeners();
        return v;
    }

    private void initViews(View v) {
        edtEmailId = v.findViewById(R.id.edtEmailId);
        edtOtp = v.findViewById(R.id.edtOtp);
        tvResendOtp = v.findViewById(R.id.tvResendOtp);
    }

    private void setListeners() {
        imgBackArrow.setOnClickListener(this);
        tvRightButton.setOnClickListener(this);
    }
    private void setHeaderFooter(View v) {
        imgNotes = v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle = v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Verify Bank Account");

        tvRightButton= v.findViewById(R.id.tvRightButton);
        tvRightButton.setText("Submit");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.tvRightButton:
                validateEmailDetails();
                break;
            default:
                break;
        }
    }

    private void validateEmailDetails() {

    }
}
