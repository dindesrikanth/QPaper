package com.example.questionpaper.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questionpaper.Model.signinmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText nametext, password,email_id;
    RadioButton prf,govt,both;
    TextView sucess,whichjobtext;
    boolean checked;
    Button register;
    String strprf="";
    String strgovt="";
    String strprfandgovt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initview();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mjksign","kio");


                signin();

            }
        });
    }
    void initview(){
        email_id = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nametext= findViewById(R.id.name);
        sucess = findViewById(R.id.sucess);
        register = findViewById(R.id.signin);
        prf = findViewById(R.id.prf);
        govt = findViewById(R.id.govt);
        both = findViewById(R.id.both);
        whichjobtext=findViewById(R.id.whichjob);
    }
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        //register.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;

        String email = email_id.getText().toString();
        String passwordtext = password.getText().toString();
        String name = nametext.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_id.setError("enter a valid email address");
            valid = false;
        } else {
            email_id.setError(null);
        }
        if (name.isEmpty()) {
            nametext.setError("enter Your Name");
            valid = false;
        } else {
            nametext.setError(null);
        }

        if (passwordtext.isEmpty() || passwordtext.length() < 4 || passwordtext.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }
        if(checked == false){
            whichjobtext.setError("Please Select Any One Of The Following Options");
        }

        return valid;
    }
    public void Radiobuttonclick(View v){
        checked = ((RadioButton) v).isChecked();
        if(checked){
            switch(v.getId()) {
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

            }}

    }

    public  void signin(){
        String email =email_id.getText().toString();
        String pw =password.getText().toString();
        String name =nametext.getText().toString();
        if (!validate()) {
            onLoginFailed();
            return;
        }
        // register.setEnabled(false);
        //register.setVisibility(View.VISIBLE);
        final signinmodel signin = new signinmodel(email,name,strgovt,strprf,strprfandgovt,pw);
        // final signinmodel signin = new signinmodel(null,null,null,null,null,null);
        Call<signinmodel> call = RetrofitClient.getInstance().getApi().newregistration(signin);
        call.enqueue(new Callback<signinmodel>() {
            @Override
            public void onResponse(Call<signinmodel> call, Response<signinmodel> response) {
                Toast.makeText(Register.this, ""+response.body()+response.code(), Toast.LENGTH_SHORT).show();
                try{
                    if(!response.isSuccessful()){
                        signinmodel regobj = response.body();
                        Toast.makeText(Register.this, ""+response.body(), Toast.LENGTH_SHORT).show();
                        //return;
                   //     sucess.append("Thanks for your regstration!"+regobj.getUser_id());
                        sucess.setText(response.code());

                        //textview.setText("code" + response.code());
                        return;
                    }
                    /*if(regobj != null){
                        if(regobj.getResult().toLowerCase().equals("success")){
                            Toast.makeText(Edit_AccountActivity.this, "Your details successfully updated", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(Edit_AccountActivity.this, ""+loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    } else{
                        Toast.makeText(Edit_AccountActivity.this, "Invalid Response from server", Toast.LENGTH_SHORT).show();
                    }*/
                }
                catch (Exception ex){
                    sucess.setText(ex.getMessage());
                }}
            @Override
            public void onFailure(Call<signinmodel> call, Throwable t) {
                sucess.setText(t.getMessage());

            }

        });
    }}

