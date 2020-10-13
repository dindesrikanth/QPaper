package com.example.questionpaper.Screens.InfoAndSettings;

import android.os.Bundle;
import android.text.TextUtils;
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

public class ChangePasswordFragment extends Fragment implements View.OnClickListener{
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvChangePassword;
    CustomEditText edtOldPassword, edtNewPassword,edtConfirmPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.change_password_fragment, container, false);
        inItView(v);
        return v;
    }

    private void inItView(View v) {
        imgNotes=v.findViewById(R.id.imgNotes);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);

        imgBackArrow.setOnClickListener(this);
        imgNotes.setVisibility(View.GONE);
        tvHeaderTitle.setText("Change Password");

        edtOldPassword=v.findViewById(R.id.edtOldPassword);
        edtNewPassword=v.findViewById(R.id.edtNewPassword);
        edtConfirmPassword=v.findViewById(R.id.edtConfirmPassword);

        tvChangePassword= v.findViewById(R.id.tvChangePassword);
        tvChangePassword.setOnClickListener(this);
        setData();
    }
    private void setData(){

        edtOldPassword.setValueToLayout("Old Password", "");
        edtNewPassword.setValueToLayout("New Password", "");
        edtConfirmPassword.setValueToLayout("Confirm Password", "");

        edtNewPassword.setMaxLength(20);
        edtConfirmPassword.setMaxLength(20);

    }
    @Override
    public void onResume() {
        super.onResume();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        ContainerActivity.relativeCustomActionBar.setVisibility(View.VISIBLE);
    }
    private boolean validatePassword(){
        if(TextUtils.isEmpty(edtOldPassword.getEditTextValue())) {
            edtOldPassword.setEditTextErrorLabel("Enter Old password");
            return false;
        }else if(TextUtils.isEmpty(edtNewPassword.getEditTextValue())){
            edtNewPassword.setEditTextErrorLabel("Enter New password");
            return false;
        }else if(TextUtils.isEmpty(edtConfirmPassword.getEditTextValue())){
            edtConfirmPassword.setEditTextErrorLabel("Enter Confirm password");
            return false;
        }
        else if(!TextUtils.isEmpty(edtNewPassword.getEditTextValue())  && !TextUtils.isEmpty(edtConfirmPassword.getEditTextValue()) && !edtNewPassword.getEditTextValue().trim().equals(edtConfirmPassword.getEditTextValue().trim()) ){
            edtConfirmPassword.setEditTextErrorLabel("New & Confirm password should match");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id ==  R.id.tvChangePassword ||  id == R.id.imgBackArrow ){
           /* case R.id.tvChangePassword:*/
                if(validatePassword()){
                    Utility.password = edtNewPassword.getEditTextValue();
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
             /*   break;
            case R.id.imgBackArrow:
                Utility.password = edtNewPassword.getEditTextValue();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break; */
        }
    }


}
