package com.example.questionpaper.Screens.mytest.LeaderBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LeaderBoard.UserTestRanks;

import java.util.List;

public class LeaderBoardAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserTestRanks> userTestRanksList;
    public LeaderBoardAdapter(List<UserTestRanks> userTestRanksList){
        this.userTestRanksList = userTestRanksList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_tests_leader_board_adapter,parent,false);
        return new AdapterViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AdapterViewHolder vh=(AdapterViewHolder)holder;
        vh.tvUserName.setText(userTestRanksList.get(position).getUserName());
        vh.tvRank.setText(userTestRanksList.get(position).getRank());
        vh.tvMarks.setText(userTestRanksList.get(position).getMarks());
        vh.tvAmountWon.setText("Amount Won ₹"+userTestRanksList.get(position).getAmountWon());
    }

    @Override
    public int getItemCount() {
        return userTestRanksList.size();
    }

    private class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName,tvRank,tvMarks,tvAmountWon;
        public AdapterViewHolder(View v) {
            super(v);
            tvUserName=v.findViewById(R.id.tvUserName);
            tvRank=v.findViewById(R.id.tvRank);
            tvMarks=v.findViewById(R.id.tvMarks);
            tvAmountWon=v.findViewById(R.id.tvAmountWon);
        }
    }

}


