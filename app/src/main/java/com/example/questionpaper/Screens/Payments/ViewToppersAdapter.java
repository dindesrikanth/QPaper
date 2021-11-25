package com.example.questionpaper.Screens.Payments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.Payments.TopTestRanks;
import com.example.questionpaper.Response.Payments.Transactions;

import java.util.List;

public class ViewToppersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<TopTestRanks>  listData;

    public ViewToppersAdapter(List<TopTestRanks> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_toppers_adapter,parent,false);
        return new AdapterViewHolder(v2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterViewHolder vh=(AdapterViewHolder)holder;
        vh.tvRank.setText("#"+listData.get(position).getRank());
        vh.tvUserName.setText(listData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvRank,tvUserName;
        public AdapterViewHolder(View v) {
            super(v);
            tvRank=v.findViewById(R.id.tvRank);
            tvUserName=v.findViewById(R.id.tvUserName);
        }
    }

}

