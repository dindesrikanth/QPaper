package com.example.questionpaper.Screens.mytest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.ContainerActivity;
import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.R;

import java.util.ArrayList;
import java.util.List;

public class SelectNoOfMonthsFragment extends Fragment implements SelectNoOfMonthsAdapter.OnAdapterItemClicked,View.OnClickListener {

    private RecyclerView rViewCommon;
    private ImageView imgNotes,imgBackArrow;
    private TextView tvHeaderTitle,tvNext;
    List<NoOfMonthsModel> listDataToRecyclerView;
    SelectNoOfMonthsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.select_no_of_months, container, false);
        inItView(v);
        return v;
    }
    private void inItView(View v) {
        rViewCommon= v.findViewById(R.id.rViewCommon);
        imgNotes=v.findViewById(R.id.imgNotes);
        imgBackArrow = v.findViewById(R.id.imgBackArrow);
        tvHeaderTitle=v.findViewById(R.id.tvHeaderTitle);
        tvNext= v.findViewById(R.id.tvNext);

        imgBackArrow.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        imgNotes.setVisibility(View.GONE);
        tvHeaderTitle.setText("My Tests Statement");


        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rViewCommon.setLayoutManager(layoutManager);
        setData();
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
    private void setData() {
        listDataToRecyclerView= new ArrayList<>();
        listDataToRecyclerView.clear();
        listDataToRecyclerView.addAll(getDataToRecyclerView());

        adapter= new SelectNoOfMonthsAdapter(this,listDataToRecyclerView);
        rViewCommon.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(int position) {
        for (NoOfMonthsModel model : listDataToRecyclerView){
            if(model.getIsSelected()){
                model.setSelected(false);
                break;
            }
        }
        listDataToRecyclerView.get(position).setSelected(true);
        adapter.notifyDataSetChanged();
      //  Toast.makeText(getContext(),"item clicked..."+listDataToRecyclerView.get(position).getTitle(),Toast.LENGTH_LONG).show();
        Utility.my_test_months_data = Integer.parseInt(listDataToRecyclerView.get(position).getTitle());
    }

    private List<NoOfMonthsModel> getDataToRecyclerView(){

        List<NoOfMonthsModel> listData=new ArrayList<>();
        listData.clear();
        NoOfMonthsModel monthsModel1=new NoOfMonthsModel();
        monthsModel1.setTitle("1");
        monthsModel1.setSelected(true);
        listData.add(monthsModel1);

        NoOfMonthsModel monthsModel2=new NoOfMonthsModel();
        monthsModel2.setTitle("3");
        monthsModel2.setSelected(false);
        listData.add(monthsModel2);

        NoOfMonthsModel monthsModel3=new NoOfMonthsModel();
        monthsModel3.setTitle("6");
        monthsModel3.setSelected(false);
        listData.add(monthsModel3);

        NoOfMonthsModel monthsModel4=new NoOfMonthsModel();
        monthsModel4.setTitle("9");
        monthsModel4.setSelected(false);
        listData.add(monthsModel4);

        NoOfMonthsModel monthsModel5=new NoOfMonthsModel();
        monthsModel5.setTitle("12");
        monthsModel5.setSelected(false);
        listData.add(monthsModel5);

        return listData;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.tvNext:
                     getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.imgBackArrow:
                     getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
        }
    }
}
