package com.example.questionpaper.Screens.mytest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questionpaper.R;
import com.example.questionpaper.Response.mytests.LiveTest.TestData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LiveTestAdapter extends RecyclerView.Adapter<LiveTestAdapter.ViewHolder> {

    List<TestData> dataList;
    Context context;

    public LiveTestAdapter(Context context, List<TestData> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public LiveTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.live_test_adapterlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveTestAdapter.ViewHolder holder, int position) {
        if(dataList!=null){
            for(int i =0;i<dataList.get(position).getTests().size();i++){
                holder.tv_tesname.setText(dataList.get(position).getTests().get(i).getName());
            }
            holder.tv_courseName.setText(dataList.get(position).getCourseName());
        }
        holder.btn_takeTest.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_tesname,tv_courseName;
        Button btn_takeTest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tesname =(TextView)itemView.findViewById(R.id.tv_test_name);
            tv_courseName  = (TextView)itemView.findViewById(R.id.tv_cource_name);
            btn_takeTest = (Button)itemView.findViewById(R.id.take_test);
        }
    }
}
