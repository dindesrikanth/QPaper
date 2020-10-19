package com.example.questionpaper.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.Login.RegisterApiRequest;
import com.example.questionpaper.Response.Login.LoginApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText edtUserName, edtPassword, edtEmail, edtMobileNo, edtReEnterPassword;
    RadioButton prf, govt, both;
    TextView sucess, whichjobtext;
    boolean checked;
    Button register;
    String strprf = "";
    String strgovt = "";
    String strprfandgovt = "";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pDialog = Utility.getProgressDialog(this);
        initview();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mjksign", "kio");
                registerAPI();
            }
        });
    }

    void initview() {
        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtMobileNo = findViewById(R.id.edtMobileNo);
        edtPassword = findViewById(R.id.edtPassword);
        edtReEnterPassword = findViewById(R.id.edtReEnterPassword);

        sucess = findViewById(R.id.sucess);
        register = findViewById(R.id.signin);
        prf = findViewById(R.id.prf);
        govt = findViewById(R.id.govt);
        both = findViewById(R.id.both);
        whichjobtext = findViewById(R.id.whichjob);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        //register.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        String name = edtUserName.getText().toString();
        String email = edtEmail.getText().toString();
        String mobileNo = edtMobileNo.getText().toString();
        String passwordtext = edtPassword.getText().toString();
        String reEnterPassword = edtReEnterPassword.getText().toString();

        if (name.isEmpty()) {
            edtUserName.setError("Enter Your Name");
            valid = false;
        } else {
            edtUserName.setError(null);
        }
        if (email.isEmpty() || !Utility.isValidEmail(email)) {
            edtEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }
        if (mobileNo.isEmpty()) {
            edtMobileNo.setError("Enter Mobile No");
            valid = false;
        } else {
            edtMobileNo.setError(null);
        }

        if (!passwordtext.equalsIgnoreCase(reEnterPassword)) {
            edtReEnterPassword.setError("Password not match");
            valid = false;
        } else {
            edtPassword.setError(null);
        }
     /*   if (checked == false) {
            whichjobtext.setError("Please Select Any One Of The Following Options");
        }*/

        if(!prf.isChecked() && !govt.isChecked() && !both.isChecked()){
            whichjobtext.setError("Please Select Any One Of The Following Options");
            valid = false;
        }
        return valid;
    }

    public void Radiobuttonclick(View v) {
        checked = ((RadioButton) v).isChecked();
        if (checked) {
            switch (v.getId()) {
                case R.id.prf:
                    if (checked)
                        strprf = "Y";
                    strgovt = "N";
                    strprfandgovt = "N";
                    break;
                case R.id.govt:
                    if (checked)
                        strgovt = "Y";
                    strprf = "N";
                    strprfandgovt = "N";
                    break;
                case R.id.both:
                    if (checked)
                        strprfandgovt = "Y";
                    strprf = "N";
                    strgovt = "N";
                    break;

            }
        }

    }

    public void registerAPI() {
        String name = edtUserName.getText().toString();
        String email = edtEmail.getText().toString();
        String mobileNo = edtMobileNo.getText().toString();
        String password = edtPassword.getText().toString();
        String examPref = "";
        if (prf.isChecked()) {
            examPref = "prof";
        } else if (govt.isChecked()) {
            examPref = "govt";
        } else {
            examPref = "prof,govt";
        }
        if (!validate()) {
            //onLoginFailed();
            return;
        }

        registerApiCall(name, email, mobileNo, password, examPref);

    }

    private void registerApiCall(String name, String email, String mobileNo, String password, String examPref) {
        pDialog.show();
        final RegisterApiRequest userTestRequest = new RegisterApiRequest(name, email, mobileNo, password, examPref);
        Call<LoginApiResponse> call = RetrofitClient.getInstance().getApi().registerApiRequest(userTestRequest);
        call.enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid details...", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
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
            Toast.makeText(getApplicationContext(), "Registered Successfully ..", Toast.LENGTH_LONG).show();

            SharedPreferences spf= Utility.getSharedPreference(getApplicationContext());
            SharedPreferences.Editor editor = spf.edit();
            editor.putString("loginId",edtEmail.getText().toString().trim());
            editor.commit();

            startActivity(new Intent(Register.this, LoginActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "invalid details.." + response.getErrorMsg(), Toast.LENGTH_LONG).show();
        }
    }

}
