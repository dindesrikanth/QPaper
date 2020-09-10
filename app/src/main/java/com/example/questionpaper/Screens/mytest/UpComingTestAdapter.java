package com.example.questionpaper.Screens.mytest;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.UpComing.Tests;

import java.util.List;

public class UpComingTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    UpComingTestFragment upComingTestFragment;
    List<Tests> tests;
    public UpComingTestAdapter(UpComingTestFragment upComingTestFragment, List<Tests> tests){
        this.upComingTestFragment=upComingTestFragment;
        this.tests=tests;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mytests_upcoming_adapter,parent,false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterViewHolder vh=(AdapterViewHolder)holder;
        vh.tvTestName.setText(tests.get(position).getName());
        vh.tvDate.setText(tests.get(position).getDate());
        vh.tvTime.setText(tests.get(position).getTestTime());
        String marksAndDuration="";
        if(!TextUtils.isEmpty(tests.get(position).getTotalMarks())){
            marksAndDuration = marksAndDuration+tests.get(position).getTotalMarks()+" Marks ";
        }
        if(!TextUtils.isEmpty(tests.get(position).getDuration())){
            marksAndDuration = marksAndDuration+tests.get(position).getDuration()+" Minutes";
        }
        vh.tvMarksAndDuration.setText(marksAndDuration);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }


    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestName,tvDate,tvTime,tvMarksAndDuration;
        public AdapterViewHolder(View v) {
            super(v);
            tvTestName=v.findViewById(R.id.tvTestName);
            tvDate=v.findViewById(R.id.tvDate);
            tvTime=v.findViewById(R.id.tvTime);
            tvMarksAndDuration=v.findViewById(R.id.tvMarksAndDuration);
        }
    }
}
