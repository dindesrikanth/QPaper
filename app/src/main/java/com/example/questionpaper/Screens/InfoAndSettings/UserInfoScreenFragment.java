package com.example.questionpaper.Screens.InfoAndSettings;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.CustomAdapter;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.InfoAndSettings.InputRequest;
import com.example.questionpaper.Response.InfoAndSettings.UserInfoScreenResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoScreenFragment extends Fragment implements View.OnClickListener {
    private CustomEditText edtName,edtEmail,edtDob,edtAddress,edtCity,edtPinCode,edtCountry;
    private EditText edtPassword,edtMobileNo;

    private ImageView imgEditPassword,imgMobileNo;
    private ToggleButton toggleNotification,toggleSuspend;
    private Spinner spnState;
    private TextView tvLogout,tvUpdateProfile;
    private ContainerActivity activity;
    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_settings_fragment, container, false);
        this.activity=(ContainerActivity)getActivity();
        pDialog=Utility.getProgressDialog(getActivity());
        inItView(v);
        setClickEnable();
        setData();
        getUserInfoData("3");
        return v;
    }

    private void setClickEnable() {
        tvUpdateProfile.setOnClickListener(this);
        imgEditPassword.setOnClickListener(this);
        imgMobileNo.setOnClickListener(this);
    }

    private void inItView(View v) {
        edtName=v.findViewById(R.id.edtName);
        edtEmail=v.findViewById(R.id.edtEmail);
        edtPassword=v.findViewById(R.id.edtPassword);
        imgEditPassword = v.findViewById(R.id.imgEditPassword);

        edtDob=v.findViewById(R.id.edtDob);
        edtMobileNo=v.findViewById(R.id.edtMobileNo);
        imgMobileNo= v.findViewById(R.id.imgMobileNo);

        edtAddress = v.findViewById(R.id.edtAddress);
        edtCity = v.findViewById(R.id.edtCity);
        edtPinCode = v.findViewById(R.id.edtPinCode);

        spnState=v.findViewById(R.id.spnState);
        edtCountry = v.findViewById(R.id.edtCountry);
        toggleNotification=v.findViewById(R.id.toggleNotification);
        toggleSuspend=v.findViewById(R.id.toggleSuspend);
        tvLogout=v.findViewById(R.id.tvLogout);
        tvLogout.setPaintFlags(tvLogout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvUpdateProfile = v.findViewById(R.id.tvUpdateProfile);
    }
    private void setData(){
        edtName.setValueToLayout("Name", "");
        edtEmail.setValueToLayout("Email", "");

        edtDob.setValueToLayout("DOB", "");

        edtAddress.setValueToLayout("Address","");
        edtCity.setValueToLayout("City","");
        edtPinCode.setValueToLayout("Pin code","");
        edtCountry.setValueToLayout("Country","India");

        setStates();
    }

    private void setStates(){
        CustomAdapter customAdapter=new CustomAdapter(getContext(), Utility.listOfStates);
        spnState.setAdapter(customAdapter);

        spnState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               // getCompletedTestData(Utility.listOfMonths[position]+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // nothing
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.tvUpdateProfile:
                if(Utility.isValidEmail(edtEmail.getEditTextValue())){
                    Toast.makeText(getActivity(),"valid...",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"not valid...",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.imgEditPassword:
                activity.displayFragment(5);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        edtPassword.setText(Utility.password);
    }


    private void getUserInfoData(String userId){
        pDialog.show();
        final InputRequest userTestRequest = new InputRequest(userId);
        Call<UserInfoScreenResponse> call = RetrofitClient.getInstance().getApi().userInfoAndSettingsAPI();
        call.enqueue(new Callback<UserInfoScreenResponse>() {
            @Override
            public void onResponse(Call<UserInfoScreenResponse> call, Response<UserInfoScreenResponse> response) {
                try {
                    if (response.isSuccessful()) {
                       // Toast.makeText(getActivity(),"header"+response.headers(),Toast.LENGTH_LONG).show();
                        showData(response.body());
                    }else{
                        Toast.makeText(getActivity(),"Failed to load API...",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserInfoScreenResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(UserInfoScreenResponse response) {
        if(response !=null){
            edtName.setValueToLayout("Name", response.getName());
            edtEmail.setValueToLayout("Email", response.getUserEmailId());
            edtPassword.setText(response.getPassword());

            edtDob.setValueToLayout("DOB", response.getDob());

            edtAddress.setValueToLayout("Address",response.getAddress());
            edtCity.setValueToLayout("City",response.getCity());
            edtPinCode.setValueToLayout("Pin code",response.getPincode());
            edtCountry.setValueToLayout("Country","India");

            if(!TextUtils.isEmpty(response.getAccountStatus()) && response.getAccountStatus().contains("Y")){
                toggleSuspend.setChecked(true);
            }else{
                toggleSuspend.setChecked(false);
            }

            if(!TextUtils.isEmpty(response.getReceiveNotifications()) && response.getReceiveNotifications().contains("Y")){
                toggleNotification.setChecked(true);
            }else{
                toggleNotification.setChecked(false);
            }
        }else{
            Toast.makeText(getActivity(),"else condition...",Toast.LENGTH_LONG).show();
        }
    }

}
