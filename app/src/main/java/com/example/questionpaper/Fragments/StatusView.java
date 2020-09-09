package com.example.questionpaper.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Activity.TestActivity;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;


public class StatusView extends Fragment implements View.OnClickListener {

    private FragmentAListener listener;
    StatusGridViewAdapter statusGridViewAdapter;
    StatusListViewAdapter statusListViewAdapter;
    List<Questionesmodel> item;
    Context context;
    Button backButton, submitButton;
    TextView attemptedCount, markedCount, unseenCount, seenCount;
    int currentQuestionPosition;
    FrameLayout parentLayout, content_layout;
    TextView grid_view, list_view;
    RecyclerView grid_recyclerView, list_recyclerView;

    public interface FragmentAListener {
        void onInputASent(int input);
        void submitTest();
    }

    public void setData(List<Questionesmodel> item, Context context, int currentQuestionPosition){
        this.item = item;
        this.context = context;
        this.currentQuestionPosition = currentQuestionPosition;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.status_view, container, false);
        grid_view = (TextView) myview.findViewById(R.id.grid_view);
        list_view = (TextView) myview.findViewById(R.id.list_view);
        grid_recyclerView = (RecyclerView) myview.findViewById(R.id.grid_recyclerView);
        list_recyclerView = (RecyclerView) myview.findViewById(R.id.list_recyclerView);
        parentLayout = myview.findViewById(R.id.parentLayout);
        content_layout = myview.findViewById(R.id.content_layout);
        content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
            }
        });
        backButton = myview.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
            }
        });
        submitButton = myview.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if(fm.getBackStackEntryCount()>0) {
                    fm.popBackStack();
                }
                listener.submitTest();
            }
        });
        grid_view.setOnClickListener(this);
        list_view.setOnClickListener(this);
        attemptedCount = myview.findViewById(R.id.attemptedCount);
        markedCount = myview.findViewById(R.id.markedCount);
        unseenCount = myview.findViewById(R.id.unseenCount);
        seenCount = myview.findViewById(R.id.seenCount);

        int attemptCount = 0,markCount = 0, seenCountNum = 0, unSeenCountNum = 0;
        for (int i=0; i<item.size(); i++) {
            if(item.get(i).isMarked()){
                markCount += 1;
            }

            if(!TextUtils.isEmpty(item.get(i).getAnswerId())){
                attemptCount += 1;
            }

            if(item.get(i).isSeen()){
                seenCountNum += 1;
            }

        }
        unSeenCountNum = item.size() - seenCountNum;
        seenCountNum = seenCountNum - attemptCount;
        attemptedCount.setText(attemptCount + "");
        markedCount.setText(markCount + "");
        unseenCount.setText(unSeenCountNum + "");
        seenCount.setText(seenCountNum + "");

        statusGridViewAdapter = new StatusGridViewAdapter(item, getContext(),currentQuestionPosition);
        statusGridViewAdapter.setOnItemClickListener(new StatusGridViewAdapter.OnItemClickListenerbtn() {
            @Override
            public void quesclick(int position) {
                listener.onInputASent(position);
            }
        });
        GridLayoutManager GridLayoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        grid_recyclerView.setLayoutManager(GridLayoutManager);
        grid_recyclerView.setAdapter(statusGridViewAdapter);

        statusListViewAdapter = new StatusListViewAdapter(item, getContext(),currentQuestionPosition);
        statusListViewAdapter.setOnItemClickListener(new StatusListViewAdapter.OnItemClickListenerbtn() {
            @Override
            public void quesclick(int position) {
                listener.onInputASent(position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list_recyclerView.setLayoutManager(linearLayoutManager);
        list_recyclerView.setAdapter(statusListViewAdapter);
//need to
        return myview;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentAListener) {
            listener = (FragmentAListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentAListener");
        }
        ((RelativeLayout)((TestActivity)getActivity()).findViewById(R.id.test_layout)).setAlpha(0.2f);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        ((RelativeLayout)((TestActivity)getActivity()).findViewById(R.id.test_layout)).setAlpha(1.0f);
    }

    void onItemClick(int pos){
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.grid_view:
                grid_recyclerView.setVisibility(View.VISIBLE);
                list_recyclerView.setVisibility(View.GONE);
                grid_view.setBackground(context.getResources().getDrawable(R.drawable.box_border_grey_bg));
                list_view.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
                break;

            case R.id.list_view:
                grid_recyclerView.setVisibility(View.GONE);
                list_recyclerView.setVisibility(View.VISIBLE);
                list_view.setBackground(context.getResources().getDrawable(R.drawable.box_border_grey_bg));
                grid_view.setBackground(context.getResources().getDrawable(R.drawable.box_border_white_bg));
                break;
        }
    }

}
