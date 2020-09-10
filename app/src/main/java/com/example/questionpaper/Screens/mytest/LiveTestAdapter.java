package com.example.questionpaper.Screens.mytest;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTest.Tests;

import java.util.ArrayList;
import java.util.List;

public class LiveTestAdapter extends RecyclerView.Adapter<LiveTestAdapter.ViewHolder> {

    private  List<Tests> dataList;
    private Context context;

    public LiveTestAdapter(Context context, ArrayList<Tests> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public LiveTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.live_test_adapterlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveTestAdapter.ViewHolder holder, int position) {

        if(!TextUtils.isEmpty(dataList.get(position).getName())){
            holder.tvTestName.setText(dataList.get(position).getName());
        }else{
            holder.tvTestName.setText("-");
        }

        if(!TextUtils.isEmpty(dataList.get(position).getCourseName())){
            holder.tvCourseName.setText(dataList.get(position).getCourseName());
        }else{
            holder.tvCourseName.setText("-");
        }

        holder.btnTakeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Test is Ready To Start", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTestName,tvCourseName;
        private Button btnTakeTest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestName =(TextView)itemView.findViewById(R.id.tvTestName);
            tvCourseName  = (TextView)itemView.findViewById(R.id.tvCourseName);
            btnTakeTest = (Button)itemView.findViewById(R.id.btnTakeTest);
        }
    }
}
