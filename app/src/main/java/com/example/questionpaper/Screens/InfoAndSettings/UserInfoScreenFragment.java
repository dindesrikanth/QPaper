package com.example.questionpaper.Screens.InfoAndSettings;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionpaper.Common.CustomAdapter;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;

public class UserInfoScreenFragment extends Fragment implements View.OnClickListener {

    private CustomEditText edtName,edtEmail,edtPassword,edtDob,edtMobileNo,edtAddress,edtCity,edtPinCode,edtCountry;
    private ToggleButton toggleNotification,toggleSuspend;
    private Spinner spnState;
    private TextView tvLogout,tvUpdateProfile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.info_settings_fragment, container, false);
        inItView(v);
        setData();
        return v;
    }
    private void inItView(View v) {
        edtName=v.findViewById(R.id.edtName);
        edtEmail=v.findViewById(R.id.edtEmail);
        edtPassword=v.findViewById(R.id.edtPassword);
        edtDob=v.findViewById(R.id.edtDob);
        edtMobileNo=v.findViewById(R.id.edtMobileNo);
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
        edtPassword.setValueToLayout("Password", "");
        edtDob.setValueToLayout("DOB", "");

        edtMobileNo.setValueToLayout("Mobile","");
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
                break;
            case R.id.imgBackArrow:
                break;
            default:
                break;
        }
    }
}
