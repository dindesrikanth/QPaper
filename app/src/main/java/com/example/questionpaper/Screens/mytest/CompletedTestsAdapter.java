package com.example.questionpaper.Screens.mytest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTest.Tests;

import java.util.List;

public class CompletedTestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    CompletedTestsFragment completedTestsFragment;
    List<Tests> tests;
    public CompletedTestsAdapter(CompletedTestsFragment completedTestsFragment, List<Tests> tests){
        this.completedTestsFragment=completedTestsFragment;
        this.tests=tests;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mytests_completed_test_adapter,parent,false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterViewHolder vh=(AdapterViewHolder)holder;
        vh.tvTestName.setText(tests.get(position).getName());
        vh.tvDate.setText(tests.get(position).getDate());
        vh.tvTime.setText(tests.get(position).getTestTime());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }


    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestName,tvDate,tvTime,tvDetailedAnalysis;
        public AdapterViewHolder(View v) {
            super(v);
            tvTestName=v.findViewById(R.id.tvTestName);
            tvDate=v.findViewById(R.id.tvDate);
            tvTime=v.findViewById(R.id.tvTime);
            tvDetailedAnalysis=v.findViewById(R.id.tvDetailedAnalysis);
        }
    }
}


