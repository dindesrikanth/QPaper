package com.example.questionpaper.Screens.mytest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.DetailedAnalysis;

import java.util.List;

public class DetailedAnalysisAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DetailedAnalysis> detailedAnalysesList;
    public DetailedAnalysisAdapter(List<DetailedAnalysis> detailedAnalysesList){
        this.detailedAnalysesList = detailedAnalysesList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.left_bold_text_right_normal_text_layout,parent,false);
            return new AdapterViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            AdapterViewHolder vh=(AdapterViewHolder)holder;
            vh.tvLeftText.setText(detailedAnalysesList.get(position).getTitle());
            vh.tvRightText.setText(":  "+ detailedAnalysesList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return detailedAnalysesList.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeftText,tvRightText;
        public AdapterViewHolder(View v) {
            super(v);
            tvLeftText=v.findViewById(R.id.tvLeftText);
            tvRightText=v.findViewById(R.id.tvRightText);
        }
    }

}

