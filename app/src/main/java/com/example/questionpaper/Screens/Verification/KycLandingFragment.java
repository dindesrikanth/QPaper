package com.example.questionpaper.Screens.Verification;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;

public class KycLandingFragment extends Fragment implements View.OnClickListener {
    private ContainerActivity activity;
    private ProgressDialog pDialog;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle;

    LinearLayout lnrEmail,lnrPanCard,lnrAddress,lnrBank;
    TextView tvEmailStatus,tvPanStatus,tvAddressStatus,tvBankStatus;
    ImageView imgEmail,imgPanCard,imgAddress,imgBank;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.kyc_landing_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        pDialog= Utility.getProgressDialog(getActivity());
        initViews(v);
        setListeners();
        return v;
    }

    private void initViews(View v) {
        imgNotes=v.findViewById(R.id.imgNotes);
        imgNotes.setVisibility(View.GONE);

        imgBackArrow = v.findViewById(R.id.imgBackArrow);

        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
        tvHeaderTitle.setText("Verify Identity");

        lnrEmail = v.findViewById(R.id.lnrEmail);
        tvEmailStatus = v.findViewById(R.id.tvEmailStatus);
        imgEmail = v.findViewById(R.id.imgEmail);

        lnrPanCard = v.findViewById(R.id.lnrPanCard);
        tvPanStatus=v.findViewById(R.id.tvPanStatus);
        imgPanCard = v.findViewById(R.id.imgPanCard);

        lnrAddress = v.findViewById(R.id.lnrAddress);
        tvAddressStatus = v.findViewById(R.id.tvAddressStatus);
        imgAddress = v.findViewById(R.id.imgAddress);

        lnrBank = v.findViewById(R.id.lnrBank);
        tvBankStatus=v.findViewById(R.id.tvBankStatus);
        imgBank = v.findViewById(R.id.imgBank);
    }

    private void setListeners(){
        imgBackArrow.setOnClickListener(this);
        lnrEmail.setOnClickListener(this);
        lnrPanCard.setOnClickListener(this);
        lnrAddress.setOnClickListener(this);
        lnrBank.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.lnrEmail :
                break;
            case R.id.lnrPanCard :
                activity.displayFragment(10);
                break;

            case R.id.lnrAddress :
                break;

            case R.id.lnrBank :
                activity.displayFragment(11);
                break;
            case R.id.imgBackArrow:
                getActivity().getSupportFragmentManager().popBackStack();
                //activity.loadLandingFragment();
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
}
