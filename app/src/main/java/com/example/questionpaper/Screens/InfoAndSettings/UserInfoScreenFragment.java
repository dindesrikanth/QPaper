package com.example.questionpaper.Screens.InfoAndSettings;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.questionpaper.Requests.InfoAndSettings.UpdateProfileRequest;
import com.example.questionpaper.Response.InfoAndSettings.UpdateProfileResponse;
import com.example.questionpaper.Response.InfoAndSettings.UserInfoScreenResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoScreenFragment extends Fragment implements View.OnClickListener {
    private CustomEditText edtName,edtEmail,edtAddress,edtCity,edtPinCode,edtCountry,edtPreferredExams;
    private EditText edtPassword,edtMobileNo,edtDob;
    private TextView tvMobileError,tvDobError;
    private ImageView imgEditPassword,imgMobileNo;
    private ToggleButton toggleNotification,toggleSuspend;
    private Spinner spnState,spnGender;
    private TextView tvLogout,tvUpdateProfile;
    private ContainerActivity activity;
    private ProgressDialog pDialog;
    DatePickerDialog picker;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_settings_fragment, container, false);
        this.activity=(ContainerActivity)getActivity();
        pDialog=Utility.getProgressDialog(getActivity());
        inItView(v);
        setLength();
        setClickEnable();
        setData();
        setTextChange();
        getUserInfoData("3");
        return v;
    }

    private void setTextChange() {
        edtMobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No implementation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Empty
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No implementation
                tvMobileError.setText("");
                tvMobileError.setVisibility(View.GONE);
            }
        });
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
        tvDobError= v.findViewById(R.id.tvDobError);
        edtMobileNo=v.findViewById(R.id.edtMobileNo);
        tvMobileError = v.findViewById(R.id.tvMobileError);
        imgMobileNo= v.findViewById(R.id.imgMobileNo);

        edtAddress = v.findViewById(R.id.edtAddress);
        edtCity = v.findViewById(R.id.edtCity);
        edtPinCode = v.findViewById(R.id.edtPinCode);

        spnState=v.findViewById(R.id.spnState);
        spnGender= v.findViewById(R.id.spnGender);
        edtCountry = v.findViewById(R.id.edtCountry);
        edtPreferredExams= v.findViewById(R.id.edtPreferredExams);

        toggleNotification=v.findViewById(R.id.toggleNotification);
        toggleSuspend=v.findViewById(R.id.toggleSuspend);
        tvLogout=v.findViewById(R.id.tvLogout);
        tvLogout.setPaintFlags(tvLogout.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvUpdateProfile = v.findViewById(R.id.tvUpdateProfile);
    }
    private void setLength(){
        edtName.setMaxLength(30);
        edtEmail.setMaxLength(30);
        edtAddress.setMaxLength(60);
        edtCity.setMaxLength(30);
        edtPreferredExams.setMaxLength(100);

        edtPinCode.setInputTypeAndLength("number",6);
        edtCountry.setMaxLength(30);
    }
    private void setData(){
        edtName.setValueToLayout("Name", "");
        edtEmail.setValueToLayout("Email", "");
        edtAddress.setValueToLayout("Address","");
        edtCity.setValueToLayout("City","");
        edtPinCode.setValueToLayout("Pin code","");
        edtCountry.setValueToLayout("Country","India");

        edtPreferredExams.setValueToLayout("Preferred Exams","");

        CustomAdapter customAdapter=new CustomAdapter(getContext(), Utility.listOfGender);
        spnGender.setAdapter(customAdapter);

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
                if(validate()){
                    updateUserProfile();
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
            edtMobileNo.setText(response.getMobileNumber());

            edtPreferredExams.setValueToLayout("Preferred Exams", response.getPreferedExams());
            edtDob.setText(response.getDob());

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

    private boolean validate(){
        if(TextUtils.isEmpty(edtName.getEditTextValue())){
            edtName.setEditTextErrorLabel("Enter name");
            return false;
        }
        if(TextUtils.isEmpty(edtEmail.getEditTextValue()) || !Utility.isValidEmail(edtEmail.getEditTextValue()) ){
            edtEmail.setEditTextErrorLabel("Enter Valid EmailId");
            return false;
        }
      /*  if(TextUtils.isEmpty(edtPassword.getText())){
            edtPassword.set("Enter Password");
            return false;
        }*/
        if(TextUtils.isEmpty(edtDob.getText())){
            tvDobError.setVisibility(View.VISIBLE);
            tvDobError.setText("Enter DOB");
            return false;
        }
        if(TextUtils.isEmpty(edtMobileNo.getText())){
            tvMobileError.setVisibility(View.VISIBLE);
            tvMobileError.setText("Invalid mobile No");
            return false;
        }
        if(TextUtils.isEmpty(edtPreferredExams.getEditTextValue())){
            edtPreferredExams.setEditTextErrorLabel("Enter Preferred Exams");
            return false;
        }
        if(TextUtils.isEmpty(edtAddress.getEditTextValue())){
            edtAddress.setEditTextErrorLabel("Enter Address");
            return false;
        }
        if(TextUtils.isEmpty(edtCity.getEditTextValue())){
            edtCity.setEditTextErrorLabel("Enter City");
            return false;
        }
        if(TextUtils.isEmpty(edtPinCode.getEditTextValue())){
            edtPinCode.setEditTextErrorLabel("Enter PinCode");
            return false;
        }
       /* if(TextUtils.isEmpty(spnState.getSelectedItem().toString())){
            state.setEditTextErrorLabel("Enter DOB");
            return false;
        }*/
        if(TextUtils.isEmpty(edtCountry.getEditTextValue())){
            edtCountry.setEditTextErrorLabel("Enter Country");
            return false;
        }
        return true;
    }
    private void updateUserProfile(){
        pDialog.show();

        String acct_status = "";
        if(toggleSuspend.isChecked()){
            acct_status = "Y";
        }else{
            acct_status = "N";
        }

        String receive_notification = "";
        if(toggleNotification.isChecked()){
            receive_notification = "Y";
        }else{
            receive_notification = "N";
        }

        final UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest(
                edtAddress.getEditTextValue(),
                edtCity.getEditTextValue(),
                edtCountry.getEditTextValue(),
                edtDob.getText().toString(),
                edtEmail.getEditTextValue(),
                spnGender.getSelectedItem().toString(),
                edtMobileNo.getText().toString(),
                edtName.getEditTextValue(),
                edtPassword.getText().toString(),
                edtPinCode.getEditTextValue(),
                edtPreferredExams.getEditTextValue(),receive_notification,
                spnState.getSelectedItem().toString(),acct_status,"3"
                );
        Call<UpdateProfileResponse> call = RetrofitClient.getInstance().getApi().updateUserProfileAPI(updateProfileRequest);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                try {
                    if (response.isSuccessful()) {
                         Toast.makeText(getActivity(),"Submitted successfully",Toast.LENGTH_LONG).show();
                       // showData(response.body());
                    }else{
                        Toast.makeText(getActivity(),"Failed to load API...",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                pDialog.dismiss();
                return;
            }
        });
    }
}
