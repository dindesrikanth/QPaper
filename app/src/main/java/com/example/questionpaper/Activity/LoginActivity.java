package com.example.questionpaper.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Common.Constants;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.Login.LoginApiRequest;
import com.example.questionpaper.Response.Login.LoginApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomEditText edtUserName, edtPassword;
    private Button btnLogIn;
    private TextView tvForgotPassword,tvSignIn;

    SharedPreferences sp;
    TextView sucess;
    SharedPreferences sharedPreferences;

    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pDialog =Utility.getProgressDialog(this);
        init();
        sp = getSharedPreferences("login",MODE_PRIVATE);
        edtUserName.setValueToLayout("Email Id", "hari123@gmail.com");
        edtPassword.setValueToLayout("Password","hari123");

        //edtPassword.setInpu
        /// From registration flow
        Bundle b = getIntent().getExtras();
        if(b!=null){
           String loginId = b.getString("loginId");
           edtUserName.setEditTextValue(loginId);
        }
    }
    private void init(){
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogIn = findViewById(R.id.btnLogIn);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignIn = findViewById(R.id.tvSignIn);
        sucess = findViewById(R.id.sucess);
        btnLogIn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);
    }

    public  void logInClicked(){
        String uname =edtUserName.getEditTextValue().trim();
        String pw =edtPassword.getEditTextValue().trim();
        if(!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(pw)){
            loginApiCall(uname,pw);
        }else{
           // Toast.makeText(getApplicationContext(),"Please enter details...",Toast.LENGTH_LONG).show();
            Utility.showCommonMessage(getApplicationContext(),"Please enter details...");
        }
    }

    private void loginApiCall(String email, String password){
        pDialog.show();
        final LoginApiRequest userTestRequest = new LoginApiRequest(email, password);
        Call<LoginApiResponse> call = RetrofitClient.getInstance().getApi().loginApiRequest(userTestRequest);
        call.enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        // Toast.makeText(getApplicationContext(),"Invalid details...",Toast.LENGTH_LONG).show();
                        Utility.showCommonMessage(getApplicationContext(),"Invalid details...");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(LoginApiResponse response) {
        if (response != null && response.getStatus().equalsIgnoreCase("success")) {
            saveUserDetailsToSPF(response.getUserId(),response.getCourseSelecetd());
            startActivity(new Intent(LoginActivity.this,ContainerActivity.class));
        }else{
            //Toast.makeText(getApplicationContext(),"invalid details.."+response.getErrorMsg(),Toast.LENGTH_LONG).show();
            Utility.showCommonMessage(getApplicationContext(),"invalid details.."+response.getErrorMsg());
        }
    }

    private void saveUserDetailsToSPF(String userId, boolean courseSelecetd){
        SharedPreferences spf= Utility.getSharedPreference(getApplicationContext());
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("loginId",edtUserName.getEditTextValue());
        editor.putBoolean(Constants.isCoursesLoaded_key,courseSelecetd);
        editor.putString(Constants.userId_key,userId);
        Constants.userId_value = userId;
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnLogIn){
           logInClicked();
        }
        else if(id == R.id.tvForgotPassword){

        }
        else if(id == R.id.tvSignIn){
            Intent Register = new Intent(LoginActivity.this,Register.class);
            startActivity(Register);
        }
    }
}
