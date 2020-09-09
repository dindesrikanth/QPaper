package com.example.questionpaper.Fragments;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Adapter.Recycler_view_adapter;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.CategoryViewHolder> {

    private List<Questionesmodel> answersList;
    Context context;
    private RecylerViewAdapter.OnItemClickListenerbtn mListeners;
    public interface OnItemClickListenerbtn {
        void quesclick(String checkid);
    }
    public void setOnItemClickListener(RecylerViewAdapter.OnItemClickListenerbtn listener) {
        mListeners = listener;
    }

    public RecylerViewAdapter(List<Questionesmodel> answersList, Context context) {
        this.answersList = answersList;
        this.context = context;
    }

    public void updateData(List<Questionesmodel> answersList) {
        this.answersList = answersList;
        notifyDataSetChanged();
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_view, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryProductView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,final int position) {
        holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.grey));
        holder.tvQuestionNumber.setText(answersList.get(position).getQues_local_id()+"");
//        if (answersList.get(position).getQues_Status() == 1) {
//            holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.red));
//        } else if (answersList.get(position).getQues_Status() == 2) {
//            holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.green));
//        }else if (answersList.get(position).getQues_Status() == 3) {
//            holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.grey));}
//        else if (answersList.get(position).getQues_Status() == 4){
//            holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.cardview_dark_background));
//        }//else {
         //   holder.tvQuestionNumber.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        //}
        holder.tvQuestionNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = answersList.get(position).getQues_local_id().toString();
                Log.e("pro",productName);
                mListeners.quesclick(productName);
                //notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestionNumber;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionNumber = itemView.findViewById(R.id.tvQuestionNumber);
        }
    }
}