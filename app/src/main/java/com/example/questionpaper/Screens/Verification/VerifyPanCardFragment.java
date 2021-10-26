package com.example.questionpaper.Screens.Verification;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Constants;
import com.example.questionpaper.Common.CustomEditText;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Requests.Verification.PanSubmitRequest;
import com.example.questionpaper.Response.CommonResponse;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class VerifyPanCardFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {
    private ContainerActivity activity;
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvAttachPan,tvAttachedFileInfo,tvVerify;

    private CustomEditText edtFullName,edtPanNumber;
    private EditText edtDob;
    private TextView dobErrorLabel;

    static final int REQUEST_IMAGE_CAPTURE = 0;
    private String base64String="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.verify_pan_card,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        setHeaderFooter(v);
        initViews(v);
        setListeners();
        return v;
    }

    private void setHeaderFooter(View v) {
        imgNotes = v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle = v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Verify PAN Card");

        tvVerify= v.findViewById(R.id.tvRightButton);
        tvVerify.setText("Verify");
    }

    private void initViews(View v) {
        edtFullName = v.findViewById(R.id.edtFullName);
        edtPanNumber = v.findViewById(R.id.edtPanNumber);
        edtDob = v.findViewById(R.id.edtDob);
        dobErrorLabel = v.findViewById(R.id.dobErrorLabel);

        tvAttachPan = v.findViewById(R.id.tvAttachPan);
        tvAttachedFileInfo = v.findViewById(R.id.tvAttachedFileInfo);


        edtFullName.setValueToLayoutNew("Full Name", "","");
        edtPanNumber.setValueToLayoutNew("PAN Number", "","");

        edtPanNumber.setMaxLength(10);
        edtFullName.setMaxLength(20);
    }
    private void setListeners() {
        imgBackArrow.setOnClickListener(this);
        tvVerify.setOnClickListener(this);
        tvAttachPan.setOnClickListener(this);

        edtDob.setOnClickListener(this);
        edtDob.setCursorVisible(false);
        edtDob.setFocusable(false);

    }

    private void setDatePicker(){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String date = year + "-"+(monthOfYear + 1) + "-" + dayOfMonth;
                        edtDob.setText(date);
                        dobErrorLabel.setVisibility(View.GONE);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.tvAttachPan:
               readPhotos();
                break;
            case R.id.edtDob:
                setDatePicker();
                break;
            case R.id.tvRightButton:
                validatePanDetails();
                break;
            default:
                break;
        }
    }
    public void readPhotos(){
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
            else
            {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream .toByteArray();
                        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                        long timestamp = System.currentTimeMillis() / 1000;
                        tvAttachedFileInfo.setText(timestamp+"");
                        tvAttachedFileInfo.setVisibility(View.VISIBLE);

                        base64String = encoded;

                        //Toast.makeText(getActivity(),"camera:"+encoded,Toast.LENGTH_LONG).show();
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri imageUri = data.getData();
                        try
                        {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , imageUri);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream .toByteArray();
                            String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                            base64String = encoded;
                           // Toast.makeText(getActivity(),"gallery:"+encoded,Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            //handle exception
                        }
                        //imageView.setImageURI(imageUri);
                    }
                    long timestamp = System.currentTimeMillis() / 1000;
                    tvAttachedFileInfo.setVisibility(View.VISIBLE);
                    tvAttachedFileInfo.setText(timestamp+"");
                    break;
            }
        }
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

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(hasFocus)
        setDatePicker();
    }

    private void validatePanDetails(){
        boolean enableSubmit = true;
        if(TextUtils.isEmpty(edtFullName.getEditTextValue())){
            edtFullName.setEditTextErrorLabel("Enter Full Name");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(edtPanNumber.getEditTextValue())){
            edtPanNumber.setEditTextErrorLabel("Enter PAN Number");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(base64String)){
            Toast.makeText(getContext(),"Please attach PAN card",Toast.LENGTH_LONG).show();
            enableSubmit =false;
        }

        if(TextUtils.isEmpty(edtDob.getText().toString())){
            dobErrorLabel.setVisibility(View.VISIBLE);
            dobErrorLabel.setText("Please enter DOB");
            enableSubmit =false;
        }else{
            dobErrorLabel.setVisibility(View.GONE);
        }

        if(!enableSubmit){
            return;
        }else{
            submitPanDetails();
        }

    }

    private void submitPanDetails(){
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
        PanSubmitRequest reviewRequest=new PanSubmitRequest(edtDob.getText().toString(),base64String,edtFullName.getEditTextValue(),
                edtPanNumber.getEditTextValue(),userId);

        Call<CommonResponse> call = RetrofitClient.getInstance().getApi().submitPanVerificationDetails(reviewRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        //Toast.makeText(getActivity(),"header"+response.headers(),Toast.LENGTH_LONG).show();
                        showData(response.body());
                    }else{
                        Utility.showCommonMessage(getActivity(),"Failed to load API...");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                // Toast.makeText(getActivity(),"Response failed ...",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }

    private void showData(CommonResponse response) {
            if(response !=null && response.getStatus().equalsIgnoreCase("success")) {
                Utility.showCommonMessage(getActivity(),"PAN Details submitted successfully ...");
                Utility.popBackStackWithDelay(getActivity().getSupportFragmentManager(), Constants.popBackStackDelayValue);
              //  getActivity().getSupportFragmentManager().popBackStack();
            }else{
                // Toast.makeText(getActivity(),"failed to submit course details...",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getActivity(),"failed to submit PAN details...");
            }
    }


}
