package com.example.questionpaper.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Model.Loginmodel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
private CustomEditText edtUserName, edtPassword;
    String KEY_SAVED_G = "SAVE_G";
    String KEY_SAVED_USER_ID = "userId";
    SharedPreferences sp;
    TextView sucess;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        sucess = findViewById(R.id.sucess);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        edtUserName.setValueToLayout("Email Id", "");
        edtPassword.setValueToLayout("Password","");

      //  if(sp.getBoolean("logged",false)){
          // Intent mainscreen = new Intent(Login_Activity.this,MainActivity.class);
        //    startActivity(mainscreen);
      //  }
    }
    public  void signin(View v){
        String uname =edtUserName.getEditTextValue();
        String pw =edtPassword.getEditTextValue();

        final Loginmodel login = new Loginmodel(uname,pw);
        Call<Loginmodel> call = RetrofitClient.getInstance().getApi().signinnewuser(login);
        call.enqueue(new Callback<Loginmodel>() {
            @Override
            public void onResponse(Call<Loginmodel> call, Response<Loginmodel> response) {
                try {
                    if (response.isSuccessful()) {
                        Loginmodel resobj = response.body();
                       // resobj.getUser_email_id()
                       Toast toast = Toast.makeText(LoginActivity.this,resobj.getMessage(),Toast.LENGTH_LONG);
                       //toast.setMargin(50,50);
                       toast.show();
                       /*if(resobj.getIs_govt_job().equals("Y")){
                          //sp.edit().putString("is_govt_job","G");
                           Savepreferences(KEY_SAVED_G,"G");
                           String S = sharedPreferences.getString(KEY_SAVED_G, null);
                           Log.d("key",S);
                       }
                        if(resobj.getIs_prof_job().equals("Y")){
//sp.edit().putString("is_pro_job","P");
                            Savepreferences(KEY_SAVED_G,"P");
                        }
                        if(resobj.getIs_both().equals("Y")){
                            Savepreferences(KEY_SAVED_G,"B");
                        }*/
                       if(resobj.getMessage().equals("Login Successful")) {
                           Long user_id_pref = resobj.getUser_id();
                           if(resobj.getIs_govt_job().equals("Y")){
                               resobj.setGovt_job("G");
                               //Savepreferences(KEY_SAVED_G,"G");
                           }
                           if(resobj.getIs_prof_job().equals("Y")){
                               resobj.setProf_job("P");
                               //Savepreferences(KEY_SAVED_G,"P");
                           }
                           Savepreferenceslong(KEY_SAVED_USER_ID,user_id_pref);

                           saveUserDetailsToSPF();

                            sucess.append("Thanks for your regstration!"+resobj.getMessage() + ","+resobj.getIs_govt_job()+","+resobj.getIs_prof_job()+","+resobj.getIs_both());
                           Intent mainscreen = new Intent(LoginActivity.this, Load_cources.class);
                           startActivity(mainscreen);
                           //sp.edit().putBoolean("logged", true).apply();
                       }
                        return;
                    }
                }catch (Exception ex){
                    Log.i("exdcf",ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Loginmodel> call, Throwable t) {
                sucess.setText(t.getMessage());
            }
        });
    }
    public void sigInToMain(View v){
        Intent Register = new Intent(LoginActivity.this,Register.class);
        startActivity(Register);
    }
    private  void Savepreferences(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    private  void Savepreferenceslong(String key, Long value){
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    private void saveUserDetailsToSPF(){
        SharedPreferences spf= Utility.getSharedPreference(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loginId",edtUserName.getEditTextValue());
        editor.commit();
    }

}