package com.example.questionpaper.Fragments;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.R;

import java.util.List;

public class StatusListViewAdapter extends RecyclerView.Adapter<StatusListViewAdapter.CategoryViewHolder> {

    private List<Questionesmodel> answersList;
    Context context;
    private StatusListViewAdapter.OnItemClickListenerbtn mListeners;
    int currentQuestionPosition;

    public interface OnItemClickListenerbtn {
        void quesclick(int position);
    }
    public void setOnItemClickListener(StatusListViewAdapter.OnItemClickListenerbtn listener) {
        mListeners = listener;
    }

    public StatusListViewAdapter(List<Questionesmodel> answersList, Context context, int currentQuestionPosition) {
        this.answersList = answersList;
        this.context = context;
        this.currentQuestionPosition = currentQuestionPosition;
    }

    public void updateData(List<Questionesmodel> answersList) {
        this.answersList = answersList;
        notifyDataSetChanged();
    }


    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryProductView);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,final int position) {
        Questionesmodel hero = answersList.get(position);
        holder.grid_item_text.setTextColor(context.getResources().getColor(R.color.black));
        holder.grid_item_marked.setVisibility(View.GONE);
        if(hero.isSeen()) {
            holder.grid_item_background.setBackground(context.getResources().getDrawable(R.drawable.round_button_grey));
        }else{
            holder.grid_item_background.setBackground(context.getResources().getDrawable(R.drawable.round_button_grey_transparent));
        }
        if(!TextUtils.isEmpty(hero.getAnswerId())){
            holder.grid_item_background.setBackground(context.getResources().getDrawable(R.drawable.round_button_blue));
            holder.grid_item_text.setTextColor(context.getResources().getColor(R.color.white));
        }

        if(position == currentQuestionPosition){
            holder.grid_item_background.setBackground(context.getResources().getDrawable(R.drawable.round_button_blue_border));
            holder.grid_item_text.setTextColor(context.getResources().getColor(R.color.test_button_blue));
        }
        if(hero.isMarked()){
            holder.grid_item_marked.setVisibility(View.VISIBLE);
        }
        holder.grid_item_text.setText(position + 1 + "");
        holder.question_text.setText(hero.getQues_detail());
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
        holder.grid_item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListeners.quesclick(position);
                //notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return answersList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView grid_item_text, question_text;
        ImageView grid_item_background;
        LinearLayout grid_item_parent;
        ImageView grid_item_marked;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            grid_item_text = itemView.findViewById(R.id.grid_item_text);
            grid_item_background = itemView.findViewById(R.id.grid_item_background);
            grid_item_parent = itemView.findViewById(R.id.grid_item_parent);
            grid_item_marked = itemView.findViewById(R.id.grid_item_marked);
            question_text = itemView.findViewById(R.id.question_text);
        }
    }
}