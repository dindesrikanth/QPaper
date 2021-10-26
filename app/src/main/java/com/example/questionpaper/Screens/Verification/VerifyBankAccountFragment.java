package com.example.questionpaper.Screens.Verification;

import android.Manifest;
import android.app.AlertDialog;
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
import com.example.questionpaper.Requests.Verification.BankAccountSubmitRequest;
import com.example.questionpaper.Response.CommonResponse;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class VerifyBankAccountFragment extends Fragment implements View.OnClickListener {
    private ContainerActivity activity;
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvVerify;

    private CustomEditText edtFullName,edtBankName,edtAccountNumber,edtConfirmAccountNumber,edtIfsc,edtBankBranch,edtBankState;
    private TextView tvAttachBank,tvAttachedFileInfo;
    private String base64String="";
    static final int REQUEST_IMAGE_CAPTURE = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.verify_bank_account,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        setHeaderFooter(v);
        initViews(v);
        setListeners();
        return v;
    }


    private void initViews(View v) {
        edtFullName = v.findViewById(R.id.edtFullName);
        edtBankName = v.findViewById(R.id.edtBankName);
        edtAccountNumber = v.findViewById(R.id.edtAccountNumber);
        edtConfirmAccountNumber = v.findViewById(R.id.edtConfirmAccountNumber);
        edtIfsc = v.findViewById(R.id.edtIfsc);

        edtBankBranch = v.findViewById(R.id.edtBankBranch);
        edtBankState = v.findViewById(R.id.edtBankState);


        edtFullName.setValueToLayout("Full name as per bank record", "");
        edtAccountNumber.setValueToLayout("Account number", "");
        edtConfirmAccountNumber.setValueToLayout("Confirm account number", "");

        edtBankName.setValueToLayout("Full bank Name", "");
        edtBankBranch.setValueToLayout("Bank branch", "");
        edtBankState.setValueToLayout("Bank state", "");
        edtIfsc.setValueToLayout("IFSC Code", "");


        tvAttachBank = v.findViewById(R.id.tvAttachBank);
        tvAttachedFileInfo = v.findViewById(R.id.tvAttachedFileInfo);

    }
    private void setListeners() {
        imgBackArrow.setOnClickListener(this);
        tvVerify.setOnClickListener(this);
        tvAttachBank.setOnClickListener(this);
    }

    private void setHeaderFooter(View v) {
        imgNotes = v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle = v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Verify Bank Account");

        tvVerify= v.findViewById(R.id.tvRightButton);
        tvVerify.setText("Verify");
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.tvAttachBank:
                readPhotos();
                break;
            case R.id.tvRightButton:
                validateBankDetails();
                break;
            default:
                break;
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

    private void validateBankDetails(){
        boolean enableSubmit = true;
        if(TextUtils.isEmpty(edtFullName.getEditTextValue())){
            edtFullName.setEditTextErrorLabel("Enter Full Name");
            enableSubmit =false;
        }


        if(TextUtils.isEmpty(edtAccountNumber.getEditTextValue())){
            edtAccountNumber.setEditTextErrorLabel("Enter Account Number as per bank record");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(edtConfirmAccountNumber.getEditTextValue())){
            edtConfirmAccountNumber.setEditTextErrorLabel("Enter Confirm Account number");
            enableSubmit =false;
        }
        if(!TextUtils.isEmpty(edtAccountNumber.getEditTextValue()) && (!TextUtils.isEmpty(edtConfirmAccountNumber.getEditTextValue())) &&
            !edtAccountNumber.getEditTextValue().trim().equals(edtConfirmAccountNumber.getEditTextValue())){
            edtConfirmAccountNumber.setValueToLayout("Confirm account number", "");
            edtConfirmAccountNumber.setEditTextErrorLabel("Account Number & confirm Account Number didn't matched");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(edtBankName.getEditTextValue())){
            edtBankName.setEditTextErrorLabel("Enter Bank Name");
            enableSubmit =false;
        }


        if(TextUtils.isEmpty(edtBankBranch.getEditTextValue())){
            edtBankBranch.setEditTextErrorLabel("Enter Bank branch");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(edtBankState.getEditTextValue())){
            edtBankState.setEditTextErrorLabel("Enter Bank state");
            enableSubmit =false;
        }


        if(TextUtils.isEmpty(edtIfsc.getEditTextValue())){
            edtIfsc.setEditTextErrorLabel("Enter IFSC code");
            enableSubmit =false;
        }
        if(TextUtils.isEmpty(base64String)){
            Toast.makeText(getContext(),"Please attach Check/Bank statement card",Toast.LENGTH_LONG).show();
            enableSubmit = false;
        }

        if(!enableSubmit){
            return;
        }else{
            submitBankAccountDetails();
        }

    }

    private void submitBankAccountDetails(){
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getContext());
        BankAccountSubmitRequest reviewRequest=new BankAccountSubmitRequest(edtFullName.getEditTextValue(),
                edtAccountNumber.getEditTextValue(),base64String,
                edtBankBranch.getEditTextValue(),edtIfsc.getEditTextValue(),edtBankName.getEditTextValue(),
                edtBankState.getEditTextValue(),userId);

        Call<CommonResponse> call = RetrofitClient.getInstance().getApi().submitBankVerificationDetails(reviewRequest);
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
            Utility.showCommonMessage(getActivity(),"Bank Details submitted successfully ...");
            Utility.popBackStackWithDelay(getActivity().getSupportFragmentManager(), Constants.popBackStackDelayValue);
            //  getActivity().getSupportFragmentManager().popBackStack();
        }else{
            // Toast.makeText(getActivity(),"failed to submit course details...",Toast.LENGTH_LONG).show();
            Utility.showCommonMessage(getActivity(),"failed to submit bank details...");
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

                      //  Toast.makeText(getActivity(),"camera:"+encoded,Toast.LENGTH_LONG).show();
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

}
