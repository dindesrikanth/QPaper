package com.example.questionpaper.Screens.mytest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//import butterknife.BindView;

public class MyTestsLandingFragment extends Fragment implements View.OnClickListener {
    ImageView imgBackArrow,imgNotes;
    TextView tvHeaderTitle;
    TabLayout tabLayout;
    ViewPager viewPager;
    MyTestsLandingAdapter adapter;
    ContainerActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.mytests_landing_fragment,container,false);
        this.activity=(ContainerActivity) getActivity();
        initialiseView(v);
        setData();
        return v;
    }
    private void initialiseView(View view) {

        imgBackArrow=view.findViewById(R.id.imgBackArrow);
        tvHeaderTitle=view.findViewById(R.id.tvHeaderTitle);
        imgNotes = view.findViewById(R.id.imgNotes);

        tabLayout= view.findViewById(R.id.tabLayout);
        viewPager=view.findViewById(R.id.viewPager2);

        imgBackArrow.setOnClickListener(this);
        imgNotes.setOnClickListener(this);


    }
    private void setData() {
        tvHeaderTitle.setText("My Tests");

        List<String> tabsList=new ArrayList<>();
        tabsList.clear();
        tabsList.add("Upcoming");
        tabsList.add("Live");
        tabsList.add("Completed");

        viewPager.setOffscreenPageLimit(tabsList.size());
        if(adapter == null){
            adapter=new MyTestsLandingAdapter(getChildFragmentManager(),tabsList);
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
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
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.imgBackArrow:
               // activity.displayFragment(4);
                getFragmentManager().popBackStack();

                break;
            case R.id.imgNotes:
                activity.displayFragment(1);
                // showMonthsSelectionDialog();
                break;
            default:
                break;
        }
    }

    private void showMonthsSelectionDialog(){
        final Dialog d=new Dialog(getContext());
        d.setContentView(R.layout.popup_testes_data_months);
        TextView tvHeaderTitle=d.findViewById(R.id.tvHeaderTitle);
        Spinner spinnerMonths=d.findViewById(R.id.spinnerMonths);
        TextView tvOkButton=d.findViewById(R.id.tvOkButton);
        tvOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
               //Toast.makeText(getContext(),tabLayout.getSelectedTabPosition()+"",Toast.LENGTH_LONG).show();
                Utility.showCommonMessage(getContext(),tabLayout.getSelectedTabPosition()+"");
            }
        });

        tvHeaderTitle.setText("Choose time period to Tests records");
        setDataToMonthsSpinner(spinnerMonths);
        d.show();
    }
    private void setDataToMonthsSpinner(final Spinner spinnerMonths) {
       // CustomAdapter customAdapter=new CustomAdapter(getContext(), Utility.listOfMonths);
       // spinnerMonths.setAdapter(customAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, Utility.listOfMonths);
        spinnerMonths.setAdapter(adapter);

        spinnerMonths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedItemView, int position, long id) {
                String selectedItemText = (String) adapterView.getItemAtPosition(position);
                Utility.my_test_months_data = Integer.parseInt(selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }



}
