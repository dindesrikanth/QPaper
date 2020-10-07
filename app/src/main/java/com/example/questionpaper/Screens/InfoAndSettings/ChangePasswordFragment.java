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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordFragment extends Fragment implements View.OnClickListener{
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvChangePassword;
    CustomEditText edtOldPassword, edtNewPassword,edtConfirmPassword;

    /*^ represents starting character of the string.
                (?=.*[0-9]) represents a digit must occur at least once.
                (?=.*[a-z]) represents a lower case alphabet must occur at least once.
                (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
                (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
                (?=\\S+$) white spaces don’t allowed in the entire string.
                .{8, 20} represents at least 8 characters and at most 20 characters.
        $ represents the end of the string.*/
   // private String regEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

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
        Pattern p = Pattern.compile(PASSWORD_PATTERN);
        Matcher m =null;
        if(!TextUtils.isEmpty(edtNewPassword.getEditTextValue())){
            m = p.matcher(edtNewPassword.getEditTextValue());
        }

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
        }else if(!m.matches()){

            edtNewPassword.setEditTextErrorLabel("Invalid password pattern");
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
