package com.example.questionpaper.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Common.Constants;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.Login.ForgotPasswordRequest;
import com.example.questionpaper.Response.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordLandingActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvRightButton;

    private CustomEditText edtEmailId,edtOtp;
    private TextView  tvResendOtp;
    private boolean isSubmitButtonClicked,isPasswordBlockEnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_landing);
        pDialog= Utility.getProgressDialog(this);
        setHeaderFooter();
        initViews();
        setListeners();
        setData();
    }

    private void setData() {
        edtEmailId.setValueToLayout("EmailId", "");
        edtOtp.setValueToLayout("OTP", "");
        edtOtp.setVisibility(View.GONE);
        tvResendOtp.setText("Resend OTP");
    }

    private void initViews() {
        edtEmailId = findViewById(R.id.edtEmailId);
        edtOtp = findViewById(R.id.edtOtp);
        tvResendOtp = findViewById(R.id.tvResendOtp);
    }
    private void setListeners() {
        imgBackArrow.setOnClickListener(this);
        tvRightButton.setOnClickListener(this);
        tvResendOtp.setOnClickListener(this);

        // default view invisible.
        edtOtp.setVisibility(View.GONE);
        tvResendOtp.setVisibility(View.GONE);
    }

    private void setHeaderFooter() {
        imgNotes = findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = findViewById(R.id.imgBackArrow);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Forgot Password");

        tvRightButton= findViewById(R.id.tvRightButton);
        tvRightButton.setText("Continue");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvRightButton:
                SubmitClicked();
                break;
            case R.id.tvResendOtp:
                Constants.forgotPasswordEmailId = edtEmailId.getEditTextValue();
                resendTextClicked();
                break;
            case R.id.imgBackArrow:
                finish();
                break;
        }
    }

    private void resendTextClicked(){
        edtOtp.setVisibility(View.VISIBLE);
    }
    private void SubmitClicked(){
        if(!isSubmitButtonClicked && validate()){
            Constants.forgotPasswordEmailId = edtEmailId.getEditTextValue();
            sendOtpAPI();
        }
    }



    private void sendOtpAPI(){
        if(pDialog !=null) {
            pDialog.show();
        }
        ForgotPasswordRequest forgotPasswordRequest=new ForgotPasswordRequest(null,
                edtEmailId.getEditTextValue().trim(),null,null,null);

        Call<CommonResponse> call = RetrofitClient.getInstance().getApi().sendOtpApi(forgotPasswordRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        Utility.showCommonMessage(getApplicationContext(),"Failed to load API...");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                // Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getApplicationContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(CommonResponse response) {
        if (response != null && response.getStatus().equalsIgnoreCase("EMAIL_SENT_SUCCESSFULLY")) {
            Utility.showCommonMessage(getApplicationContext(),"EMAIL SENT SUCCESSFULLY  ...");
            isSubmitButtonClicked = true;
            edtOtp.setVisibility(View.VISIBLE);
            edtOtp.setEditTextValue("");
            tvResendOtp.setVisibility(View.VISIBLE);
            tvRightButton.setText("Verify OTP");
        }else{
            Utility.showCommonMessage(getApplicationContext(),"Failed ...");
        }
    }

    public boolean validate() {
        boolean valid = true;
        String email = edtEmailId.getEditTextValue();
        if (email.isEmpty() || !Utility.isValidEmail(email.trim())) {
            edtEmailId.setEditTextErrorLabel("Enter a valid email address");
            valid = false;
        }else if (isSubmitButtonClicked && TextUtils.isEmpty(edtOtp.getEditTextValue().trim())){
            edtOtp.setEditTextErrorLabel("Enter OTP");
            valid = false;
        }
        return valid;
    }

}
