package com.example.questionpaper.Screens.mytest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;

public class UpComingTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public UpComingTestAdapter(){

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
        vh.tvTestName.setText("Test Name "+ position);
        vh.tvDate.setText("Date "+ position);
        vh.tvTime.setText("Time "+ position);
        vh.tvDuration.setText("Duration "+ position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestName,tvDate,tvTime,tvDuration;
        public AdapterViewHolder(View v) {
            super(v);
            tvTestName=v.findViewById(R.id.tvTestName);
            tvDate=v.findViewById(R.id.tvDate);
            tvTime=v.findViewById(R.id.tvTime);
            tvDuration=v.findViewById(R.id.tvDuration);
        }
    }
}
