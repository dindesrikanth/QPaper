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
import com.example.questionpaper.Requests.Login.RegisterApiRequest;
import com.example.questionpaper.Response.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    private CustomEditText edtNewPassword,edtConfirmPassword;
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvRightButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password_activity);
        initViews();
        setData();
    }
    private void initViews() {
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
    }

    private void setData() {
        edtNewPassword.setValueToLayout("New password", "");
        edtConfirmPassword.setValueToLayout("Confirm password", "");
    }

    private void setHeaderFooter() {
        imgNotes = findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = findViewById(R.id.imgBackArrow);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Password");

        tvRightButton= findViewById(R.id.tvRightButton);
        tvRightButton.setText("Submit");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvRightButton:
               //SubmitClicked();
                break;
            case R.id.imgBackArrow:
                finish();
                break;
        }
    }

    public boolean validate() {
        boolean valid = true;
        if(TextUtils.isEmpty(edtNewPassword.getEditTextValue())){
            edtNewPassword.setEditTextErrorLabel("Enter New Password");
            valid = false;
        }
        else if(TextUtils.isEmpty(edtConfirmPassword.getEditTextValue())){
            edtConfirmPassword.setEditTextErrorLabel("Enter Confirm Password");
            valid = false;
        }
        else if(!edtNewPassword.getEditTextValue().equals(edtNewPassword.getEditTextValue())){
            edtNewPassword.setEditTextErrorLabel("Password not matched");
            edtConfirmPassword.setEditTextValue("");
            valid = false;
        }
        return valid;
    }

    private void resetPasswordApi(){
        pDialog.show();
        RegisterApiRequest registerApiRequest=new RegisterApiRequest("",
                Constants.forgotPasswordEmailId,"",edtNewPassword.getEditTextValue(),"");

        Call<CommonResponse> call = RetrofitClient.getInstance().getApi().resetPasswordApi(registerApiRequest);
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

    private void showData(CommonResponse body) {
    }


}
